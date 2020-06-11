package com.eagle.relationaldbaccessapi.util.util;

import java.util.Random;

import com.eagle.relationaldbaccessapi.util.constants.FileConstants;

public class StringUtil {
	
	private StringUtil() {
	}

	private static final String ELEMENT_TO_REPLACE = "--element--";
	private static final String ID_TO_REPLACE = "--id--";
	private static final String ID_NOT_FOUND_MESSAGE = "The --element-- with id --id-- dont exist";
	
	private static final int CHARACTER_TYPES = 3;
	private static final int LOWER = 1;
	private static final int UPPER = 2; 
	private static final int ASCII_START_LOWER = 97;
	private static final int ASCII_START_UPPER = 65;
	private static final int ASCII_START_NUMBERS = 48;
	private static final int TOTAL_NUMBERS = 10;
	private static final int TOTAL_LETTERS = 26;
	
	   public static String generateRandomId(int lengId){
	        StringBuilder sb = new StringBuilder(FileConstants.ID_REFERENCE);
	        Random random = new Random();
	        for (int i=0; i<lengId; i++){
	        	int selectCharType = random.nextInt(CHARACTER_TYPES);
	        	switch(selectCharType) {
	        	case LOWER : 
	        		char lower = (char) (ASCII_START_LOWER + random.nextInt(TOTAL_LETTERS));
	        		sb.append(lower);
	        		break;
	        	case UPPER :
	        		char upper = (char) (ASCII_START_UPPER + random.nextInt((TOTAL_LETTERS)));
	          		sb.append(upper);
	        		break;
	        	default :
	        		char number =  (char) (ASCII_START_NUMBERS + random.nextInt((TOTAL_NUMBERS)));
	        		sb.append(number);
	        	}
	        }
	        return sb.toString();
	    }
	   
	   public static String badIdMessage(String typeElement,Long id) {
		   return ID_NOT_FOUND_MESSAGE
				   .replace(ELEMENT_TO_REPLACE, typeElement)
				   .replace(ID_TO_REPLACE, id.toString());
	   }
	   
	   public static String badIdMessage(String typeElement, String id) {
		   return ID_NOT_FOUND_MESSAGE
				   .replace(ELEMENT_TO_REPLACE, typeElement)
				   .replace(ID_TO_REPLACE, id);
	   }
}
