package com.eagle.relationaldbaccessapi.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.eagle.relationaldbaccessapi.models.entity.UserInfoEntity;
import com.eagle.relationaldbaccessapi.models.model.FileModel;
import com.eagle.relationaldbaccessapi.repocitory.UserInfoRepocitory;
import com.eagle.relationaldbaccessapi.services.interfaces.IUserInfo;
import com.eagle.relationaldbaccessapi.util.components.FileComponent;
import com.eagle.relationaldbaccessapi.util.constants.FileConstants;
import com.eagle.relationaldbaccessapi.util.validators.FileValidator;

@Service
public class UserInfoServiceImpl implements IUserInfo {
	
	private UserInfoRepocitory repository;
	private FileComponent fileComponent;
	
	@Autowired
	public UserInfoServiceImpl(UserInfoRepocitory repository, FileComponent fileComponent) {
		this.repository = repository;
		this.fileComponent = fileComponent;
	}

	@Override
	public UserInfoEntity insert(UserInfoEntity dto) {
		return null;
	}

	@Override
	public UserInfoEntity update(UserInfoEntity dto, Long id) {
		return null;
	}

	@Override
	public UserInfoEntity findById(Long id) {
		return null;
	}

	@Override
	public List<UserInfoEntity> findAll() {
		return null;
	}

	@Override
	public boolean deleteById(Long id) {
		return false;
	}

	@Override
	public boolean existById(Long id) {
		return false;
	}
	
	@Override
	public boolean uploadFile(FileModel fileModel) {
		if(FileValidator.validate(fileModel, FileConstants.TYPE_IMG)) {
			this.fileComponent.saveFile(fileModel.getPath(), fileModel.getMultipartFile());
			return true;
		}
		return false;
	}

	@Override
	public boolean getFile(FileModel fileMode) {
		return false;
	}

	@Override
	public boolean updateFile(FileModel fileMode, String oldFileName) {
		return false;
	}

	@Override
	public boolean deleteFile(FileModel fileMode) {
		return false;
	}
}
