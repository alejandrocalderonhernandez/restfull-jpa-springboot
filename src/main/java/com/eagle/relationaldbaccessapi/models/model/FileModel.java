package com.eagle.relationaldbaccessapi.models.model;

import java.io.File;
import java.nio.file.Path;

import org.springframework.web.multipart.MultipartFile;

import com.eagle.relationaldbaccessapi.util.constants.FileConstants;
import com.eagle.relationaldbaccessapi.util.constants.PathsConstants;
import com.eagle.relationaldbaccessapi.util.constants.RegexConstants;
import com.eagle.relationaldbaccessapi.util.util.StringUtil;

public class FileModel {
	
	private String fullName;
	private String name;
	private String extension;
	private Path path;
	private File file;
	private MultipartFile multipartFile;
	
	private static final int URI_POSITION = 0;
	private static final int NAME_POSITION = 1;
	
	public FileModel(MultipartFile multipartFile) {
		this.name = multipartFile.getOriginalFilename();
		this.extension = this.getExtension(multipartFile.getOriginalFilename());
		this.fullName = StringUtil.generateRandomId(FileConstants.LENGTH_ID_IMG) + multipartFile.getOriginalFilename();
		this.path = Path.of(PathsConstants.USER_IMG).resolve(this.fullName).toAbsolutePath();
		this.multipartFile = multipartFile;
	}
	
	public FileModel(String url) {
		String[] segmentUrl = url.split(RegexConstants.ID_REFERENCE);
		this.name = FileConstants.ID_REFERENCE + segmentUrl[NAME_POSITION];
		this.extension = this.getExtension(segmentUrl[NAME_POSITION]);
		this.fullName = url;
		this.file = Path.of(segmentUrl[URI_POSITION]).resolve(this.name).toFile();
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

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	private String getExtension(String name) {
		if(name.contains(".")) {
			return name.split(RegexConstants.FILE_EXTENSION)[1];
		} else {
		 return null;
		}
	}
}
