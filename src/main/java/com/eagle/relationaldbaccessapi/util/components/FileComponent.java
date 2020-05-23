package com.eagle.relationaldbaccessapi.util.components;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
			LOGGER.error("ERROR TO READ PATH -> " + e.getMessage());
		}
		return stringBuilder.toString();
	}
	
	public boolean saveFile(Path path, MultipartFile file) {
		try {
			Files.copy(file.getInputStream(), path);
			LOGGER.info("SUCCESS -> New file added: " + file.getOriginalFilename() );
			return true;
		} catch (IOException e) {
			LOGGER.error("ERROR TO LOAD FILE-> " + e.getMessage());
			return false;
		}
	}
	
	public Resource getFile(Path path) {
		try {
			return new UrlResource(path.toUri());
		} catch (IOException e) {
			LOGGER.error("ERROR  TO GET FILE -> " + e.getMessage());
			return null;
		}
	}
	
	public boolean deleteFile(String name, String path) {
		File file = Paths.get(path).resolve(name).toFile();
		if(file.exists() && file.canRead()) {
			if(file.delete()) {
				LOGGER.info("SUCCESS ->  file with name " + name + " deleted" );
				return true;
			} else {
				LOGGER.error("ERROR ->  Cant delete file with name " + name  );
				return false;
			}
		}
		throw new IllegalArgumentException();
	}
}
