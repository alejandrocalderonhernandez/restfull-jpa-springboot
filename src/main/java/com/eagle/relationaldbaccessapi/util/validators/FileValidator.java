package com.eagle.relationaldbaccessapi.util.validators;

import java.util.Arrays;

import com.eagle.relationaldbaccessapi.models.model.FileModel;

public class FileValidator {
	
	private FileValidator() {
	}
	
	public static boolean validate(FileModel fileModel, String[] typoModel) {
		return Arrays.stream(typoModel).filter(typo -> 
			fileModel.getExtension().equals(typo)).toArray(String[]::new).length > 0 
				&& fileModel.getFullName().length() <= 70 ;
	}
}
