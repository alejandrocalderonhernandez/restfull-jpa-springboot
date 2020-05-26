package com.eagle.relationaldbaccessapi.services.interfaces.common;

import com.eagle.relationaldbaccessapi.models.model.FileModel;

import org.springframework.core.io.Resource;


public interface IFilesService {
	
	public boolean uploadOrUpdateFile(FileModel fileModel, Long id);
	public Resource getFile(FileModel fileMode, Long id);
	public boolean deleteFile(Long id);
}
