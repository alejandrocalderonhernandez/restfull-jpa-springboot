package com.eagle.relationaldbaccessapi.util.components;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class ResponceMessages {
	
	private static final String MESSAGE = "message: ";
	private static final String ERROR = "error: ";
	private static final String DATA = "data: ";
	
	public static final String GET_SUCCES = "Get element success: ";
	public static final String CREATE_SUCCESS = "Created element success ";
	public static final String UPDATE_SUCCESS = "Updated element success ";
	public static final String DELETE_SUCCESS = "Delete element success: ";
	public static final String UPLOAD_SUCCESS = "Upload file success ";
	
	public static final String GET_ERROR = "Cant get element ";
	public static final String CREATE_ERROR = "Element not created ";
	public static final String UPDATE_ERROR = "Element not updated ";
	public static final String DELETE_ERROR = "	Element not deleted ";
	public static final String UPLOAD_ERROR = "File not upload ";
	public static final String FILE_FORMAT_ERROR = "Format incorrect ";
	public static final String SAVE_ERROR = "Error to save element ";
	
	public static final String DATABASE_EMPTY = "	Database is empty ";
	public static final String ELEMENT_NOT_FOUND = "Element not found ";
	public static final String SERVER_ERROR = "Server error ";
	
	public Map<String, Object> successMessage(String message, Object data) {
		Map<String, Object> responce = new HashMap<>();
		responce.put(MESSAGE, message);
		responce.put(DATA, data);
		return responce;
	}
	
	public Map<String, Object> errorMessage(String error, String message){
		Map<String, Object> responce = new HashMap<>();
		responce.put(ERROR, error);
		responce.put(MESSAGE, message);
		return responce;
	}
	
	public Map<String, Object> internalServerError(){
		Map<String, Object> responce = new HashMap<>();
		responce.put(ERROR, "Server error");
		return responce;
	}
}
