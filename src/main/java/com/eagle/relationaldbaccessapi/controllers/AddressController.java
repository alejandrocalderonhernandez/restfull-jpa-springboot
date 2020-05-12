package com.eagle.relationaldbaccessapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eagle.relationaldbaccessapi.models.dto.AddressDTO;
import com.eagle.relationaldbaccessapi.services.interfaces.IAddressService;
import com.eagle.relationaldbaccessapi.util.components.ResponceMessages;

import static com.eagle.relationaldbaccessapi.util.constants.RestConstants.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/address")
public class AddressController {
	
	private static final String MODEL_NAME = "Address";
	
	private IAddressService service;
	private ResponceMessages messages;
	
	@Autowired
	public  AddressController(IAddressService service, ResponceMessages messages) {
		this.service = service;
		this.messages = messages;
	}
	
	@PostMapping(path = REST_CREATE, consumes={"application/json"}) 
	public ResponseEntity<?> create(@RequestBody AddressDTO address) {
		try {
			Map<String, Object> responce = this.messages.messageCreated(this.service.insert(address));
			return new ResponseEntity<Map<String, Object>>(responce, HttpStatus.OK);
		} catch (Exception e) {
			Map<String, Object> responce = this.messages.messsageGenericError(e.getMessage());
			return new ResponseEntity<Map<String, Object>>(responce, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping(path = REST_UPDATE, produces = MediaType.APPLICATION_JSON_VALUE, consumes={"application/json"})
	public ResponseEntity<?> update(@RequestBody AddressDTO address, @PathVariable Long id) {
		try {
			if(this.service.existById(id)) {
				Map<String, Object> responce = this.messages.messageUpdated(this.service.update(address, id));
				return new ResponseEntity<Map<String, Object>>(responce, HttpStatus.OK);
			} else {
				Map<String, Object> responce = this.messages.messsageModelNotFound(id, MODEL_NAME);
				return new ResponseEntity<Map<String, Object>>(responce, HttpStatus.BAD_REQUEST);
			}
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
			List<AddressDTO> listResponce = this.service.findAll();
			if(listResponce.isEmpty()) {
				Map<String, Object> responce = this.messages.messageNoData();
				return new ResponseEntity<Map<String, Object>>(responce, HttpStatus.OK);
			} else {
				Map<String, Object> responce = this.messages.messageSuccess(listResponce);
				return new ResponseEntity<Map<String, Object>>(responce, HttpStatus.OK);
			}
		} catch (Exception e) {
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
}
