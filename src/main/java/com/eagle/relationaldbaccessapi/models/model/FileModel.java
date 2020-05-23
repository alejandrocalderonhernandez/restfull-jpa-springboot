package com.eagle.relationaldbaccessapi.models.model;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import com.eagle.relationaldbaccessapi.util.constants.AppRegexConstants;
import com.eagle.relationaldbaccessapi.util.constants.PathsConstants;

public class FileModel {
	
	private String fullName;
	private String name;
	private String extension;
	private Path path;
	private MultipartFile multipartFile;
	
	public FileModel(MultipartFile multipartFile) {
		this.name = multipartFile.getOriginalFilename();
		this.extension = this.getExtension(multipartFile.getOriginalFilename());
		this.fullName = UUID.randomUUID().toString() + multipartFile.getOriginalFilename();
		this.path = Path.of(PathsConstants.USER_IMG).resolve(this.fullName).toAbsolutePath();;
		this.multipartFile = multipartFile;
	}
	
	public String getFullName() {
		return fullName;
	}
	
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getExtension() {
		return extension;
	}
	
	public void setExtension(String extension) {
		this.extension = extension;
	}
	
	public Path getPath() {
		return path;
	}
	
	public void setPath(Path path) {
		this.path = path;
	}
	
	public MultipartFile getMultipartFile() {
		return multipartFile;
	}

	public void setMultipartFile(MultipartFile multipartFile) {
		this.multipartFile = multipartFile;
	}

	private String getExtension(String name) {
		if(name.contains(".")) {
			return name.split(AppRegexConstants.FILE_EXTENSION)[1];
		} else {
		 return null;
		}
	}
}
