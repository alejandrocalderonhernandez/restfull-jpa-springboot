package com.eagle.relationaldbaccessapi.controllers;

import static com.eagle.relationaldbaccessapi.util.constants.RestConstants.REST_CREATE;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eagle.relationaldbaccessapi.models.dto.RouteDTO;
import com.eagle.relationaldbaccessapi.services.interfaces.IRouteService;
import com.eagle.relationaldbaccessapi.util.components.ResponseMessages;

@RestController
@RequestMapping("/route")
public class RouteController {
	
	private IRouteService service;
	private ResponseMessages messages;
	
	@Autowired
	public  RouteController(IRouteService service, ResponseMessages messages) {
		this.service = service;
		this.messages = messages;
	}

	@PostMapping(path = REST_CREATE, produces = MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE) 
	public ResponseEntity<?> create(@RequestBody RouteDTO route){
		try {
			System.out.println("q pedo en el controller");
			Map<String, Object> response = 
					this.messages.successMessage(ResponseMessages.CREATE_SUCCESS, this.service.insert(route));
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			Map<String, Object> response = 
					this.messages.errorMessage(ResponseMessages.SAVE_ERROR, ResponseMessages.CREATE_ERROR);
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
