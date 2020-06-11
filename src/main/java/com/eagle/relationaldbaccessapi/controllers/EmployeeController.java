package com.eagle.relationaldbaccessapi.controllers;

import static com.eagle.relationaldbaccessapi.util.constants.RestConstants.REST_CREATE;
import static com.eagle.relationaldbaccessapi.util.constants.RestConstants.REST_FIND_BY_ID;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eagle.relationaldbaccessapi.models.dto.EmployeeDTO;
import com.eagle.relationaldbaccessapi.services.interfaces.IEmployeeService;
import com.eagle.relationaldbaccessapi.util.components.ResponceMessages;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	public static final String REST_FIND_USER_ID = "/find/userId/{id}";
	
	private IEmployeeService service;
	private ResponceMessages messages;
	
	@Autowired
	public  EmployeeController(IEmployeeService service, ResponceMessages messages) {
		this.service = service;
		this.messages = messages;
	}

	@PostMapping(path = REST_CREATE, produces = MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE) 
	public ResponseEntity<?> create(@Valid @RequestBody EmployeeDTO employee) {
		try {
			Map<String, Object> responce = 
					this.messages.successMessage(ResponceMessages.CREATE_SUCCESS, this.service.insert(employee));
			return new ResponseEntity<>(responce, HttpStatus.OK);
		} catch (Exception e) {
			Map<String, Object> responce = 
					this.messages.errorMessage(ResponceMessages.SAVE_ERROR, ResponceMessages.CREATE_ERROR);
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
			Map<String, Object> responce =this.messages.internalServerError();
			return new ResponseEntity<>(responce, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(path = REST_FIND_USER_ID, produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<?> findById(@PathVariable String id) {
		try {
			Map<String, Object> responce = 
					this.messages.successMessage(ResponceMessages.GET_SUCCES, this.service.findByAlternativeId(id));
			return new ResponseEntity<>(responce, HttpStatus.OK);
		} catch (IllegalArgumentException ie) {
				Map<String, Object> responce = 
						this.messages.errorMessage(ie.getMessage(), ResponceMessages.ELEMENT_NOT_FOUND);
				return new ResponseEntity<>(responce, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			Map<String, Object> responce =this.messages.internalServerError();
			return new ResponseEntity<>(responce, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
