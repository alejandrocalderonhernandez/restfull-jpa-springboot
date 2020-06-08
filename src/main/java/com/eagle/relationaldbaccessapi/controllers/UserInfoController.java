package com.eagle.relationaldbaccessapi.controllers;

import static com.eagle.relationaldbaccessapi.util.constants.RestConstants.REST_CREATE;
import static com.eagle.relationaldbaccessapi.util.constants.RestConstants.REST_DELETE;
import static com.eagle.relationaldbaccessapi.util.constants.RestConstants.REST_FIND_ALL;
import static com.eagle.relationaldbaccessapi.util.constants.RestConstants.REST_FIND_BY_ID;
import static com.eagle.relationaldbaccessapi.util.constants.RestConstants.REST_UPDATE;
import static com.eagle.relationaldbaccessapi.util.constants.RestConstants.REST_UPLOAD_IMG;
import static com.eagle.relationaldbaccessapi.util.constants.RestConstants.REST_DELETE_IMG;

import java.util.Map;

import javax.validation.Valid;

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
	
	private static final String UPDATE_STATUS = "update/status/{id}";
	
	private IUserInfo service;
	private ResponceMessages messages;
	
	@Autowired
	public UserInfoController(IUserInfo service, ResponceMessages messages) {
		this.messages = messages;
		this.service = service;
	}
	
	@PostMapping(path = REST_CREATE, produces = MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE) 
	public ResponseEntity<?> create(@Valid @RequestBody UserInfoDTO user) {
		try {
			Map<String, Object> responce = 
					this.messages.successMessage(ResponceMessages.CREATE_SUCCESS,this.service.insert(user));
			return new ResponseEntity<>(responce, HttpStatus.OK);
		} catch (Exception e) {
			Map<String, Object> responce = 
					this.messages.errorMessage(ResponceMessages.SAVE_ERROR, ResponceMessages.CREATE_ERROR);
			return new ResponseEntity<>(responce, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping(path = REST_UPDATE,  consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@Valid @RequestBody UserInfoDTO user, @PathVariable Long id) {
		try {
			Map<String, Object> responce = 
					this.messages.successMessage(ResponceMessages.UPDATE_SUCCESS, this.service.update(user, id));
			return new ResponseEntity<>(responce, HttpStatus.OK);
		} catch (IllegalArgumentException ie) {
			Map<String, Object> responce = 
					this.messages.errorMessage(ie.getMessage(), ResponceMessages.ELEMENT_NOT_FOUND);
			return new ResponseEntity<>(responce, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			Map<String, Object> responce = this.messages.internalServerError();
			return new ResponseEntity<>(responce, HttpStatus.INTERNAL_SERVER_ERROR);
		} 
	}
	
	@GetMapping(path = REST_FIND_BY_ID, produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<?> findById(@PathVariable Long id) {
		try {
			Map<String, Object> responce = 
					this.messages.successMessage(ResponceMessages.GET_SUCCES, this.service.findById(id));
			return new ResponseEntity<>(responce, HttpStatus.OK);
		} catch (IllegalArgumentException ie) {
				Map<String, Object> responce = 
						this.messages.errorMessage(ie.getMessage(), ResponceMessages.ELEMENT_NOT_FOUND);
				return new ResponseEntity<>(responce, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			Map<String, Object> responce = this.messages.internalServerError();
			return new ResponseEntity<>(responce, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(path = REST_FIND_ALL, produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<?> findAll() {
		try {
            Map<String, Object> responce = 
            		this.messages.successMessage(ResponceMessages.GET_SUCCES, this.service.findAll());
     		return new ResponseEntity<>(responce, HttpStatus.OK);
		} catch (IllegalArgumentException ie) {
			Map<String, Object> responce = 
					this.messages.errorMessage(ie.getMessage(), ResponceMessages.ELEMENT_NOT_FOUND);
			return new ResponseEntity<>(responce, HttpStatus.BAD_REQUEST);
		}catch (Exception e) {
			Map<String, Object> responce = this.messages.internalServerError();
			return new ResponseEntity<>(responce, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping(path = REST_DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> delete(@PathVariable Long id) {
		try {
			this.service.deleteById(id);
			Map<String, Object> responce = 
					this.messages.successMessage(ResponceMessages.DELETE_SUCCESS, id);
			return new ResponseEntity<>(responce, HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			Map<String, Object> responce = 
					this.messages.errorMessage(e.getMessage(), ResponceMessages.DELETE_ERROR);
			return new ResponseEntity<>(responce, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping(path = REST_UPLOAD_IMG, produces = MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.ALL_VALUE) 
	public ResponseEntity<?> uploadImg(@RequestParam("img") MultipartFile img, @PathVariable Long id) {
		FileModel fileModel =  new FileModel(img);
		try {
			if(this.service.uploadOrUpdateFile(fileModel, id)) {
				Map<String, Object> responce = 
						messages.successMessage(ResponceMessages.CREATE_SUCCESS, null);
				return new ResponseEntity<>(responce, HttpStatus.OK);
			}
			Map<String, Object> responce = 
					this.messages.errorMessage("Error to upload file", ResponceMessages.CREATE_ERROR);
			return new ResponseEntity<>(responce, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			Map<String, Object> responce = 
					this.messages.errorMessage(e.getMessage(), ResponceMessages.FILE_FORMAT_ERROR);
			return new ResponseEntity<>(responce, HttpStatus.BAD_REQUEST);
		}

	}
	
	@DeleteMapping(path = REST_DELETE_IMG,  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteImg(@PathVariable Long id) {
		try {
			this.service.deleteFile(id);
			Map<String, Object> responce = 
					this.messages.successMessage(ResponceMessages.DELETE_SUCCESS, id);
			return new ResponseEntity<>(responce, HttpStatus.OK);
		} catch (IllegalArgumentException ie) {
			Map<String, Object> responce = 
					this.messages.errorMessage(ie.getMessage(), ResponceMessages.DELETE_ERROR);
			return new ResponseEntity<>(responce, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			Map<String, Object> responce =	this.messages.internalServerError();
			return new ResponseEntity<>(responce, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PatchMapping(path = UPDATE_STATUS,  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateStatus(@PathVariable Long id){
		try {
			this.service.changeStatus(id);
			Map<String, Object> responce = 
					this.messages.successMessage(ResponceMessages.UPDATE_SUCCESS,this.service.findById(id));
			return new ResponseEntity<>(responce, HttpStatus.OK);
		} catch (IllegalArgumentException ie) {
			Map<String, Object> responce = 
					this.messages.errorMessage(ie.getMessage(), ResponceMessages.ELEMENT_NOT_FOUND);
			return new ResponseEntity<>(responce, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			Map<String, Object> responce = 
					this.messages.errorMessage(e.getMessage(), ResponceMessages.UPDATE_ERROR);
			return new ResponseEntity<>(responce, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
