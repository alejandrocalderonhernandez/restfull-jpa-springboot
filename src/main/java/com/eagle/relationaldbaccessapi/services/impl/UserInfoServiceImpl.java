package com.eagle.relationaldbaccessapi.services.impl;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eagle.relationaldbaccessapi.models.dto.UserInfoDTO;
import com.eagle.relationaldbaccessapi.models.entity.UserInfoEntity;
import com.eagle.relationaldbaccessapi.models.model.FileModel;
import com.eagle.relationaldbaccessapi.repository.UserInfoRepocitory;
import com.eagle.relationaldbaccessapi.services.interfaces.IUserInfo;
import com.eagle.relationaldbaccessapi.util.components.FileComponent;
import com.eagle.relationaldbaccessapi.util.constants.FileConstants;
import com.eagle.relationaldbaccessapi.util.interfaces.functional.IUpdater;
import com.eagle.relationaldbaccessapi.util.validators.FileValidator;

@Service
public class UserInfoServiceImpl implements IUserInfo {
	
    private static final Logger LOGGER = LogManager.getLogger(UserInfoServiceImpl.class);
	
	private UserInfoRepocitory repocitory;
	private FileComponent fileComponent;
	
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
		UserInfoEntity response = null;
		try {
			response = this.repocitory.save(new UserInfoEntity(dto));
			LOGGER.info("Inserted {} ", dto);
		} catch (Exception e) {
			LOGGER.error("Error to insert UserInfo: ", e);
		}
		return new UserInfoDTO(response);
	}

	@Override
	@Transactional
	public UserInfoDTO update(UserInfoDTO dto, Long id) {
		if(this.repocitory.existsById(id)) {
			try {
				UserInfoEntity userToUpdate = this.repocitory.findById(id).get();
				this.updater.update(dto, userToUpdate);
				this.repocitory.save(userToUpdate);
				LOGGER.info("Updated {} ", dto);
				return new UserInfoDTO(userToUpdate);
			} catch (Exception e) {
				LOGGER.error("Error to update UserInfo -> ", e);
				return dto;
			}
		} else {
			LOGGER.warn("Update UserInfo not found id: " + id);
			throw new IllegalArgumentException("The UserInfo id: " + id + " dont exist");
		}
	}

	@Override
	@Transactional(readOnly = true)
	public UserInfoDTO findById(Long id) {
		if(this.repocitory.existsById(id)){
			return new UserInfoDTO(this.repocitory.findById(id).get());
		} else {
			LOGGER.warn("Select UserInfo not found id: " + id);
			throw new IllegalArgumentException("The UserInfo with id:  " + id + " dont exist");
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<UserInfoDTO> findAll() {
		List<UserInfoEntity>resultEntity = this.repocitory.findAll();
		if(!resultEntity.isEmpty()) {
			return resultEntity.stream().map(UserInfoDTO::new)
					.collect(Collectors.toList());
		} else {
			LOGGER.warn("Find all no data: UserInfo");
			throw new NoSuchElementException("Database is empty");
		}
	}
	
	@Override
	@Transactional
	public boolean deleteById(Long id) {
		if(this.repocitory.existsById(id)) {
			this.repocitory.deleteById(id);
			LOGGER.info("Deleted UserInfo with id: " + id);
			return true;
		} else {
			LOGGER.warn("Delete UserInfo not found id: " + id);
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
			LOGGER.info("New file: " + id);
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
				throw new NoSuchElementException("This user not have a photo");
			}
		} else {
			throw new IllegalArgumentException("the UserInfo with id : " + id + " dont exist");
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
			throw new IllegalArgumentException("the UserInfo with id : " + id + " dont exist");
		}
	}

	@Override
	@Transactional 
	public void changeStatus(Long id) {
		if(this.repocitory.existsById(id)) {
			UserInfoEntity userToUpdate = this.repocitory.findById(id).get();
			userToUpdate.setStatus(!userToUpdate.getStatus());
			this.repocitory.save(userToUpdate);
			LOGGER.info("Updated UserInfo inactive with id " + id);
		} else {
			LOGGER.warn("Update UserInfo not found id: " + id);
			throw new IllegalArgumentException("The UserInfo with id: " + id + " dont exist");
		}
	}
}
