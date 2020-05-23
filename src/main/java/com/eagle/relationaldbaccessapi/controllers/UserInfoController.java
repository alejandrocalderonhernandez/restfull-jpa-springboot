package com.eagle.relationaldbaccessapi.controllers;

import static com.eagle.relationaldbaccessapi.util.constants.RestConstants.REST_UPLOAD_IMG;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.eagle.relationaldbaccessapi.models.model.FileModel;
import com.eagle.relationaldbaccessapi.services.interfaces.IUserInfo;
import com.eagle.relationaldbaccessapi.util.components.ResponceMessages;

@RestController
@RequestMapping("/user-info")
public class UserInfoController {
	
	private IUserInfo service;
	private ResponceMessages messages;
	
	@Autowired
	public UserInfoController(IUserInfo service, ResponceMessages messages) {
		this.messages = messages;
		this.service = service;
	}

	@PostMapping(path = REST_UPLOAD_IMG, produces = MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.ALL_VALUE) 
	public ResponseEntity<?> uploadFile(@RequestParam("img") MultipartFile img) {
		FileModel fileModel =  new FileModel(img);
		if(this.service.uploadFile(fileModel)) {
			Map<String, Object> responce = messages.messsageUploadFileSuccess(true);
			return new ResponseEntity<Map<String, Object>>(responce, HttpStatus.OK);
		}
		Map<String, Object> responce = messages.messsageUploadFileSuccess(false);
		return new ResponseEntity<Map<String, Object>>(responce, HttpStatus.BAD_REQUEST);
	}

}
