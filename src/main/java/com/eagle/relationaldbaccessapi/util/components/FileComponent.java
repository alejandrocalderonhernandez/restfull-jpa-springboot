package com.eagle.relationaldbaccessapi.util.components;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

@Component
public class FileComponent {
	
	private static final Logger LOGGER = LogManager.getLogger(FileComponent.class);
	
	public String read(Path path) {
		StringBuilder stringBuilder =  new StringBuilder();
		try (Stream<String> lines  = Files.lines(path, StandardCharsets.UTF_8)){
			lines.forEach(stringBuilder::append);
		} catch (Exception e) {
			LOGGER.error("Error to read path: " + e.getMessage());
		}
		return stringBuilder.toString();
	}
	
	public boolean saveFile(Path path, MultipartFile file) {
		try {
			Files.copy(file.getInputStream(), path);
			return true;
		} catch (IOException e) {
			LOGGER.error("Can't save file: " + e.getMessage());
			return false;
		}
	}
	
	public Resource getFile(Path path) {
		try {
			return new UrlResource(path.toUri());
		} catch (IOException e) {
			LOGGER.error("Cant get file: " + e.getMessage());
			return null;
		}
	}
	
	public boolean deleteFile(File file) {
		if(file.exists() && file.canRead()) {
			if(file.delete()) {
				return true;
			} else {
				LOGGER.error("Can't read file");
				return false;
			}
		} else {
			LOGGER.error("Can't read file");
			return false;
		}
	}
}
