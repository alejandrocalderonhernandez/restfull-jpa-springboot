package com.eagle.relationaldbaccessapi.util.components;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ResponceMessages {
	
	private static final String MESSAGE = "message: ";
	private static final String ERROR = "error: ";
	private static final String RESPONCE = "data: ";
	private static final String UPLOAD = "upload: ";
	
	private StringBuilder sb;
	
	@Autowired
	public ResponceMessages(StringBuilder sb) {
		this.sb = sb;
	}
	
	public Map<String, Object> messsageModelNotFound(Long id, String modelName) {
		Map<String, Object> responce = new HashMap<>();
		String error = this.sb.append(modelName).append(" with ").append(id).append(" was not found").toString();
		responce.put(MESSAGE,  "Object was not found");
		responce.put(ERROR,  error);
		resetStringBuilder();
		return responce;
	}	
	
	public Map<String, Object> messsageModelAlreadyExist(String modelName) {
		Map<String, Object> responce = new HashMap<>();
		String message = this.sb.append( "This ").append(modelName).append( " already exist").toString();
		responce.put(MESSAGE, message);
		resetStringBuilder();
		return responce;
	}
	
	public Map<String, Object> messsageGenericError(String errorMessage) {
		Map<String, Object> responce = new HashMap<>();
		responce.put(MESSAGE,  "Server error");
		responce.put(ERROR, errorMessage);
		return responce;
	}
	
	public Map<String, Object> messageSuccess(Object o) {
		Map<String, Object> responce = new HashMap<>();
		responce.put(MESSAGE,  "Success");
		responce.put(RESPONCE,  o);
		return responce;
	}
	
	public Map<String, Object> messageUpdated(Object o) {
		Map<String, Object> responce = new HashMap<>();
		responce.put(MESSAGE,  "Updated");
		responce.put(RESPONCE,  o);
		return responce;
	}
	
	public Map<String, Object> messageUpdated() {
		Map<String, Object> responce = new HashMap<>();
		responce.put(MESSAGE,  "Updated");
		return responce;
	}
	
	public Map<String, Object> messageCreated(Object o) {
		Map<String, Object> responce = new HashMap<>();
		responce.put(MESSAGE,  "Created");
		responce.put(RESPONCE,  o);
		return responce;
	}
	
	public Map<String, Object> messageDeleted() {
		Map<String, Object> responce = new HashMap<>();
		responce.put(MESSAGE,  "Deleted");
		return responce;
	}
	
	public Map<String, Object> messageNoData() {
		Map<String, Object> responce = new HashMap<>();
		responce.put(MESSAGE, "The database is empty");
		return responce;
	}
	
	public Map<String, Object> messsageUploadFileError(boolean upload) {
		Map<String, Object> responce = new HashMap<>();
		responce.put(MESSAGE,  "Error to upload file");
		responce.put(UPLOAD, upload);
		return responce;
	}
	
	public Map<String, Object> messsageUploadFileSuccess(boolean upload) {
		Map<String, Object> responce = new HashMap<>();
		responce.put(MESSAGE,  "Upload file success");
		responce.put(UPLOAD, upload);
		return responce;
	}
	
	public Map<String, Object> messsageGetFileError(String errorMessage) {
		Map<String, Object> responce = new HashMap<>();
		responce.put(MESSAGE,  "Error to get file");
		responce.put(ERROR,  errorMessage);
		return responce;
	}	
	
	private void resetStringBuilder(){
		this.sb.delete(0,sb.length());
	}
}
