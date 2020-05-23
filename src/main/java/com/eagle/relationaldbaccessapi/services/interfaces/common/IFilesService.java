package com.eagle.relationaldbaccessapi.services.interfaces.common;

import com.eagle.relationaldbaccessapi.models.model.FileModel;

public interface IFilesService {
	
	public boolean uploadFile(FileModel fileModel);
	public boolean getFile(FileModel fileMode);
	public boolean updateFile(FileModel fileMode,  String oldFileName);
	public boolean deleteFile(FileModel fileMode);
}
