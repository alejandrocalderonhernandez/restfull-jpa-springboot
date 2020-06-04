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
import com.eagle.relationaldbaccessapi.util.exceptions.PageabeSizeException;
import com.eagle.relationaldbaccessapi.util.util.PageableUtil;

import static com.eagle.relationaldbaccessapi.util.constants.RestConstants.*;

import java.util.Map;

import javax.validation.Valid;

@RestController
@RequestMapping("/address")
public class AddressController {
	
	private IAddressService service;
	private ResponceMessages messages;
	
	@Autowired
	public  AddressController(IAddressService service, ResponceMessages messages) {
		this.service = service;
		this.messages = messages;
	}
	
	@PostMapping(path = REST_CREATE, produces = MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE) 
	public ResponseEntity<?> create(@Valid @RequestBody AddressDTO address) {
		try {
			Map<String, Object> responce = 
					this.messages.successMessage(ResponceMessages.CREATE_SUCCESS, this.service.insert(address));
			return new ResponseEntity<>(responce, HttpStatus.OK);
		} catch (Exception e) {
			Map<String, Object> responce = 
					this.messages.errorMessage(e.getMessage(), ResponceMessages.CREATE_ERROR);
			return new ResponseEntity<>(responce, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping(path = REST_UPDATE,  consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@Valid @RequestBody AddressDTO address, @PathVariable Long id) {
		try {
			Map<String, Object> responce = 
					this.messages.successMessage(ResponceMessages.UPDATE_SUCCESS,this.service.update(address, id));
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
	
	@GetMapping(path = REST_FIND_BY_ID, produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<?> findById(@PathVariable Long id) {
		try {
			Map<String, Object> responce = 
					this.messages.successMessage(ResponceMessages.CREATE_SUCCESS, this.service.findById(id));
			return new ResponseEntity<>(responce, HttpStatus.OK);
		} catch (IllegalArgumentException ie) {
				Map<String, Object> responce = 
						this.messages.errorMessage(ie.getMessage(), ResponceMessages.ELEMENT_NOT_FOUND);
				return new ResponseEntity<>(responce, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			Map<String, Object> responce =
					this.messages.errorMessage(e.getMessage(), ResponceMessages.ELEMENT_NOT_FOUND);
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
			Map<String, Object> responce =
					this.messages.errorMessage(e.getMessage(), ResponceMessages.GET_ERROR);
			return new ResponseEntity<>(responce, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping(path = REST_DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> delete(@PathVariable Long id) {
		if(this.service.deleteById(id)) {
			Map<String, Object> responce = 
					this.messages.successMessage(ResponceMessages.DELETE_SUCCESS, id);
			return new ResponseEntity<>(responce, HttpStatus.OK);
		} else {
			Map<String, Object> responce = 
					this.messages.errorMessage("Element with id " + id + "dont exist", ResponceMessages.DELETE_ERROR);
			return new ResponseEntity<>(responce, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(path = REST_PAGE, produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<?> getPageByRange(@PathVariable Integer page, @PathVariable String size) {
		try {
			int limit = PageableUtil.getPageSize(size);
            Map<String, Object> responce = 
            		this.messages.successMessage(ResponceMessages.GET_SUCCES, this.service.getPageByRange(page, limit));
     		return new ResponseEntity<>(responce, HttpStatus.OK);
		} catch (IllegalArgumentException ie) {
			Map<String, Object> responce = 
					this.messages.errorMessage(ie.getMessage(), ResponceMessages.ELEMENT_NOT_FOUND);
			return new ResponseEntity<>(responce, HttpStatus.BAD_REQUEST);
		} catch (PageabeSizeException pe) {
				Map<String, Object> responce = 
						this.messages.errorMessage(pe.getMessage(), ResponceMessages.ELEMENT_NOT_FOUND);
				return new ResponseEntity<>(responce, HttpStatus.BAD_REQUEST);
		}catch (Exception e) {
			Map<String, Object> responce = this.messages.errorMessage(e.getMessage(), ResponceMessages.GET_ERROR);
			return new ResponseEntity<>(responce, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
