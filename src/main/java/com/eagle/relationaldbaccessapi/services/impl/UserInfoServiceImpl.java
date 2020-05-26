package com.eagle.relationaldbaccessapi.services.impl;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eagle.relationaldbaccessapi.models.dto.UserInfoDTO;
import com.eagle.relationaldbaccessapi.models.entity.UserInfoEntity;
import com.eagle.relationaldbaccessapi.models.model.FileModel;
import com.eagle.relationaldbaccessapi.repocitory.UserInfoRepocitory;
import com.eagle.relationaldbaccessapi.services.interfaces.IUserInfo;
import com.eagle.relationaldbaccessapi.util.components.FileComponent;
import com.eagle.relationaldbaccessapi.util.constants.FileConstants;
import com.eagle.relationaldbaccessapi.util.interfaces.IMapper;
import com.eagle.relationaldbaccessapi.util.interfaces.IUpdater;
import com.eagle.relationaldbaccessapi.util.validators.FileValidator;

@Service
public class UserInfoServiceImpl implements IUserInfo {
	
    private static final Logger LOGGER = LogManager.getLogger(UserInfoServiceImpl.class);
	
	private UserInfoRepocitory repocitory;
	private FileComponent fileComponent;
	
	private IMapper<UserInfoEntity, UserInfoDTO> mapToEntity = userInfoDto -> {
		return new UserInfoEntity.Builder()
				.addId(userInfoDto.getId())
				.addName1(userInfoDto.getName1())
				.addName2(userInfoDto.getName2())
				.addLastName1(userInfoDto.getLastName1())
				.addLastName2(userInfoDto.getLastName2())
				.addCurp(userInfoDto.getCurp())
				.addAge(userInfoDto.getAge())
				.build();
	};
	
	private IMapper<UserInfoDTO, UserInfoEntity> mapToDTO = userInfoEntity -> {
		return new UserInfoDTO.Builder()
				.addId(userInfoEntity.getId())
				.addName1(userInfoEntity.getName1())
				.addName2(userInfoEntity.getName2())
				.addLastName1(userInfoEntity.getLastName1())
				.addLastName2(userInfoEntity.getLastName2())
				.addCurp(userInfoEntity.getCurp())
				.addAge(userInfoEntity.getAge())
				.addPhotoUrl(userInfoEntity.getPhotoUrl())
				.addCreatedAt(userInfoEntity.getCreateAt())
				.addStatus(userInfoEntity.getStatus())
				.build();
	};
	
	private IUpdater<UserInfoDTO, UserInfoEntity> updater = (newUserInfo, oldUserInfo) -> {
		oldUserInfo.setName1(newUserInfo.getName1());
		oldUserInfo.setName2(newUserInfo.getName2());
		oldUserInfo.setLastName1(newUserInfo.getLastName1());
		oldUserInfo.setLastName2(newUserInfo.getLastName2());
		oldUserInfo.setCurp(newUserInfo.getCurp());
		oldUserInfo.setAge(newUserInfo.getAge());
	};
	
	@Autowired
	public UserInfoServiceImpl(UserInfoRepocitory repocitory, FileComponent fileComponent) {
		this.repocitory = repocitory;
		this.fileComponent = fileComponent;
	}
	
	@Override
	@Transactional
	public UserInfoDTO insert(UserInfoDTO dto) {
		try {
			this.repocitory.save(this.mapToEntity.mapObject(dto));
			LOGGER.info("[insert] inserted UserInfo new UserInfo");
		} catch (Exception e) {
			LOGGER.error("[insert] Error to insert UserInfo -> ", e);
		}
		return dto;
	}

	@Override
	@Transactional
	public UserInfoDTO update(UserInfoDTO dto, Long id) {
		if(this.repocitory.existsById(id)) {
			try {
				UserInfoEntity userToUpdate = this.repocitory.findById(id).get();
				this.updater.update(dto, userToUpdate);
				this.repocitory.save(userToUpdate);
				LOGGER.info("[update] updated UserInfo with id " + id);
				return this.mapToDTO.mapObject(userToUpdate);
			} catch (Exception e) {
				LOGGER.error("[update] error to update UserInfo -> ", e);
				return dto;
			}
		} else {
			LOGGER.error("[update] Error to update UserInfo, id -> " + id+ " don´t exist on database");
			throw new IllegalArgumentException("The UserInfo id -> " + id + " don´t exist");
		}
	}

	@Override
	@Transactional(readOnly = true)
	public UserInfoDTO findById(Long id) {
		if(this.repocitory.existsById(id)){
			return this.mapToDTO.mapObject(this.repocitory.findById(id).get());
		} else {
			LOGGER.error("[findById]: Error to get UserInfo, id -> " + id+ " don´t exist in database");
			throw new IllegalArgumentException("the UserInfo id -> " + id + " don´t exist");
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<UserInfoDTO> findAll() {
		List<UserInfoEntity>resultEntity = this.repocitory.findAll();
		if(!resultEntity.isEmpty()) {
			List<UserInfoDTO> resultDTO = new LinkedList<>();
			resultEntity.forEach(address ->  resultDTO.add(this.mapToDTO.mapObject(address)));
			return resultDTO;
		} else {
			LOGGER.error("[findAll]: Not data -> UserInfo");
			throw new NoSuchElementException("Database is empty");
		}
	}
	
	@Override
	@Transactional
	public boolean deleteById(Long id) {
		if(this.repocitory.existsById(id)) {
			this.repocitory.deleteById(id);
			LOGGER.info("[delete] UserInfo with id " + id);
			return true;
		} else {
			return false;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public boolean existById(Long id) {
		return this.repocitory.existsById(id);
	}
	
	@Override
	@Transactional
	public boolean uploadOrUpdateFile(FileModel fileModel, Long id) {
		if(FileValidator.validate(fileModel, FileConstants.TYPE_IMG) && this.repocitory.existsById(id)) {
			UserInfoEntity userToUpdate = this.repocitory.findById(id).get();
			userToUpdate.setPhotoUrl(fileModel.getPath().toString());
			this.repocitory.save(userToUpdate);
			return this.fileComponent.saveFile(fileModel.getPath(), fileModel.getMultipartFile());
		}
		return false;
	}

	@Override
	@Transactional(readOnly = true)
	public Resource getFile(FileModel fileMode, Long id) {
		if(this.repocitory.existsById(id)) {
			String url = this.repocitory.findById(id).get().getPhotoUrl();
			if(url.length() > 0) {
				Path path = Paths.get(url).toAbsolutePath();
				return this.fileComponent.getFile(path);
			} else {
				throw new NoSuchElementException("this user not have a photo");
			}
		} else {
			throw new IllegalArgumentException("the UserInfo id -> " + id + " don´t exist");
		}
	}

	@Override
	@Transactional
	public boolean deleteFile(Long id) {
		if(this.repocitory.existsById(id)) {
			UserInfoEntity userToUpdate = this.repocitory.findById(id).get();
			String url = userToUpdate.getPhotoUrl();
			FileModel fileModel = new FileModel(url);
			userToUpdate.setPhotoUrl(null);
			this.repocitory.save(userToUpdate);
			return this.fileComponent.deleteFile(fileModel.getFile());
		} else {
			throw new IllegalArgumentException("the UserInfo id -> " + id + " don´t exist");
		}
	}

	@Override
	@Transactional 
	public void changeStatus(Long id) {
		if(this.repocitory.existsById(id)) {
			UserInfoEntity userToUpdate = this.repocitory.findById(id).get();
			userToUpdate.setStatus(!userToUpdate.getStatus());
			this.repocitory.save(userToUpdate);
			LOGGER.info("[updated] UserInfo inactive with id " + id);
		} else {
			LOGGER.error("[update] Error to update UserInfo, id -> " + id+ " don´t exist on database");
			throw new IllegalArgumentException("The UserInfo id -> " + id + " don´t exist");
		}
	}
}
