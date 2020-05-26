package com.eagle.relationaldbaccessapi.controllers;

import static com.eagle.relationaldbaccessapi.util.constants.RestConstants.REST_CREATE;
import static com.eagle.relationaldbaccessapi.util.constants.RestConstants.REST_DELETE;
import static com.eagle.relationaldbaccessapi.util.constants.RestConstants.REST_FIND_ALL;
import static com.eagle.relationaldbaccessapi.util.constants.RestConstants.REST_FIND_BY_ID;
import static com.eagle.relationaldbaccessapi.util.constants.RestConstants.REST_UPDATE;
import static com.eagle.relationaldbaccessapi.util.constants.RestConstants.REST_UPLOAD_IMG;
import static com.eagle.relationaldbaccessapi.util.constants.RestConstants.REST_DELETE_IMG;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.eagle.relationaldbaccessapi.models.dto.UserInfoDTO;
import com.eagle.relationaldbaccessapi.models.model.FileModel;
import com.eagle.relationaldbaccessapi.services.interfaces.IUserInfo;
import com.eagle.relationaldbaccessapi.util.components.ResponceMessages;

@RestController
@RequestMapping("/user-info")
public class UserInfoController {
	
	private static final String MODEL_NAME = "UserInfo";
	private static final String UPDATE_STATUS = "update/status/{id}";
	
	private IUserInfo service;
	private ResponceMessages messages;
	
	@Autowired
	public UserInfoController(IUserInfo service, ResponceMessages messages) {
		this.messages = messages;
		this.service = service;
	}
	
	@PostMapping(path = REST_CREATE, produces = MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE) 
	public ResponseEntity<?> create(@RequestBody UserInfoDTO user) {
		try {
			Map<String, Object> responce = this.messages.messageCreated(this.service.insert(user));
			return new ResponseEntity<Map<String, Object>>(responce, HttpStatus.OK);
		} catch (Exception e) {
			Map<String, Object> responce = this.messages.messsageGenericError(e.getMessage());
			return new ResponseEntity<Map<String, Object>>(responce, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping(path = REST_UPDATE,  consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody UserInfoDTO user, @PathVariable Long id) {
		try {
			Map<String, Object> responce = this.messages.messageUpdated(this.service.update(user, id));
			return new ResponseEntity<Map<String, Object>>(responce, HttpStatus.OK);
		} catch (IllegalArgumentException ie) {
			Map<String, Object> responce = this.messages.messsageModelNotFound(id, MODEL_NAME);
			return new ResponseEntity<Map<String, Object>>(responce, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			Map<String, Object> responce = this.messages.messsageGenericError(e.getMessage());
			return new ResponseEntity<Map<String, Object>>(responce, HttpStatus.INTERNAL_SERVER_ERROR);
		} 
	}
	
	@GetMapping(path = REST_FIND_BY_ID, produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<?> findById(@PathVariable Long id) {
		try {
			Map<String, Object> responce = this.messages.messageSuccess(this.service.findById(id));
			return new ResponseEntity<Map<String, Object>>(responce, HttpStatus.OK);
		} catch (Exception e) {
			Map<String, Object> responce = this.messages.messsageGenericError(e.getMessage());
			return new ResponseEntity<Map<String, Object>>(responce, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(path = REST_FIND_ALL, produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<?> findAll() {
		try {
            Map<String, Object> responce = this.messages.messageSuccess(this.service.findAll());
     		return new ResponseEntity<Map<String, Object>>(responce, HttpStatus.OK);
		} catch (IllegalArgumentException ie) {
			Map<String, Object> responce = this.messages.messageNoData();
			return new ResponseEntity<Map<String, Object>>(responce, HttpStatus.NO_CONTENT);
		}catch (Exception e) {
			Map<String, Object> responce = this.messages.messsageGenericError(e.getMessage());
			return new ResponseEntity<Map<String, Object>>(responce, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping(path = REST_DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> delete(@PathVariable Long id) {
		if(this.service.deleteById(id)) {
			Map<String, Object> responce = this.messages.messageDeleted();
			return new ResponseEntity<Map<String, Object>>(responce, HttpStatus.OK);
		} else {
			Map<String, Object> responce = this.messages.messsageModelNotFound(id, MODEL_NAME);
			return new ResponseEntity<Map<String, Object>>(responce, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping(path = REST_UPLOAD_IMG, produces = MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.ALL_VALUE) 
	public ResponseEntity<?> uploadImg(@RequestParam("img") MultipartFile img, @PathVariable Long id) {
		FileModel fileModel =  new FileModel(img);
		if(this.service.uploadOrUpdateFile(fileModel, id)) {
			Map<String, Object> responce = messages.messsageUploadFileSuccess(true);
			return new ResponseEntity<Map<String, Object>>(responce, HttpStatus.OK);
		}
		Map<String, Object> responce = messages.messsageUploadFileError(false);
		return new ResponseEntity<Map<String, Object>>(responce, HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping(path = REST_DELETE_IMG,  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteImg(@PathVariable Long id) {
		try {
			this.service.deleteFile(id);
			Map<String, Object> responce = this.messages.messageDeleted();
			return new ResponseEntity<Map<String, Object>>(responce, HttpStatus.OK);
		} catch (IllegalArgumentException ie) {
			Map<String, Object> responce = messages.messsageUploadFileError(false);
			return new ResponseEntity<Map<String, Object>>(responce, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			Map<String, Object> responce = this.messages.messsageGenericError(e.getMessage());
			return new ResponseEntity<Map<String, Object>>(responce, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PatchMapping(path = UPDATE_STATUS,  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateStatus(@PathVariable Long id){
		try {
			this.service.changeStatus(id);
			Map<String, Object> responce = this.messages.messageUpdated();
			return new ResponseEntity<Map<String, Object>>(responce, HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			Map<String, Object> responce = this.messages.messsageModelNotFound(id, MODEL_NAME);
			return new ResponseEntity<Map<String, Object>>(responce, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			Map<String, Object> responce = this.messages.messsageGenericError(e.getMessage());
			return new ResponseEntity<Map<String, Object>>(responce, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
