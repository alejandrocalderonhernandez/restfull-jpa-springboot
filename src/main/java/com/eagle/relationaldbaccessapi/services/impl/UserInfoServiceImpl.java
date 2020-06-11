package com.eagle.relationaldbaccessapi.services.impl;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
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
import com.eagle.relationaldbaccessapi.util.strategies.BuilderDTOSWithRelationStrategies;
import com.eagle.relationaldbaccessapi.util.strategies.BuilderEntityWithRelationStrategies;
import com.eagle.relationaldbaccessapi.util.strategies.BuilderSimpleDTOStrategies;
import com.eagle.relationaldbaccessapi.util.strategies.BuilderSimpleEntityStrategies;
import com.eagle.relationaldbaccessapi.util.util.StringUtil;
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
			UserInfoEntity userToInsert = this.selectBuilderEntity(dto);
			response = this.repocitory.save(userToInsert);
			LOGGER.info("Inserted {} ", dto);
		} catch (Exception e) {
			LOGGER.error("Error to insert UserInfo: ", e);
		}
		return this.selectBuilderDTO(response);
	}

	@Override
	@Transactional
	public UserInfoDTO update(UserInfoDTO dto, Long id) {
		Optional<UserInfoEntity> userToUpdate = this.repocitory.findById(id);
		if(userToUpdate.isPresent()) {
			try {
				this.updater.update(dto, userToUpdate.get());
				this.repocitory.save(userToUpdate.get());
				LOGGER.info("Updated {} ", dto);
				return this.selectBuilderDTO(userToUpdate.get());
			} catch (Exception e) {
				LOGGER.error("Error to update UserInfo -> ", e);
				return null;
			}
		} else {
			LOGGER.warn("Update UserInfo not found id: " + id);
			throw new IllegalArgumentException(StringUtil.badIdMessage(TYPE, id));
		}
	}

	@Override
	@Transactional(readOnly = true)
	public UserInfoDTO findById(Long id) {
		Optional<UserInfoEntity> userToSearch= this.repocitory.findById(id);
		if(userToSearch.isPresent()){
			return this.selectBuilderDTO(userToSearch.get());
		} else {
			LOGGER.warn("Select UserInfo not found id: " + id);
			throw new IllegalArgumentException(StringUtil.badIdMessage(TYPE, id));
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<UserInfoDTO> findAll() {
		List<UserInfoEntity>resultEntity = this.repocitory.findAll();
		if(!resultEntity.isEmpty()) {
			return resultEntity.stream().map(this::selectBuilderDTO)
					.collect(Collectors.toList());
		} else {
			LOGGER.warn("Find all no data: UserInfo");
			throw new NoSuchElementException("Database is empty");
		}
	}
	
	@Override
	@Transactional
	public void deleteById(Long id) {
		if(this.repocitory.existsById(id)) {
			this.repocitory.deleteById(id);
			LOGGER.info("Deleted UserInfo with id: " + id);
		} else {
			LOGGER.warn("Delete UserInfo not found id: " + id);
			throw new IllegalArgumentException(StringUtil.badIdMessage(TYPE, id));
		}
	}
	
	@Override
	@Transactional
	public boolean uploadOrUpdateFile(FileModel fileModel, Long id) {
		Optional<UserInfoEntity> user = this.repocitory.findById(id);
		if(FileValidator.validate(fileModel, FileConstants.TYPE_IMG) && user.isPresent()) {
			UserInfoEntity userToUpdate = user.get();
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
		Optional<UserInfoEntity> user = this.repocitory.findById(id);
		if(user.isPresent()) {
			String url = user.get().getPhotoUrl();
			if(url.length() > 0) {
				Path path = Paths.get(url).toAbsolutePath();
				return this.fileComponent.getFile(path);
			} else {
				throw new NoSuchElementException("This user dont have a photo");
			}
		} else {
			throw new IllegalArgumentException(StringUtil.badIdMessage(TYPE, id));
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
			throw new IllegalArgumentException(StringUtil.badIdMessage(TYPE, id));
		}
	}

	@Override
	@Transactional 
	public void changeStatus(Long id) {
		Optional<UserInfoEntity> userToUpdate = this.repocitory.findById(id);
		if(userToUpdate.isPresent()) {
			userToUpdate.get().setStatus(!userToUpdate.get().getStatus());
			this.repocitory.save(userToUpdate.get());
			LOGGER.info("Updated UserInfo inactive with id " + id);
		} else {
			LOGGER.warn("Update UserInfo not found id: " + id);
			throw new IllegalArgumentException(StringUtil.badIdMessage(TYPE, id));
		}
	}
	
	private UserInfoEntity selectBuilderEntity(UserInfoDTO dto) {
		UserInfoEntity result = null;
		if (dto.getEmployee()!=null) {
			result = BuilderEntityWithRelationStrategies.BUILD_USER_INFO_ENTITY.build(dto);
			result.getEmployee().setUserInfo(result);
		} else {
			result = BuilderSimpleEntityStrategies.BUILD_USER_INFO_ENTITY.build(dto);
		}
		return result;
	}
	
	private UserInfoDTO selectBuilderDTO(UserInfoEntity entity) {
		if (entity.getEmployee()!=null) {
			return BuilderDTOSWithRelationStrategies.BUILD_USER_INFO_DTO.build(entity);
		} else {
			return BuilderSimpleDTOStrategies.BUILD_USER_INFO_DTO.build(entity);
		}
	}
}
