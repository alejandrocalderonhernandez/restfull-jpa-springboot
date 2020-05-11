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

@RestController
@RequestMapping("address")
public class AddressController {
	
	private IAddressService service;
	
	@Autowired
	public  AddressController(IAddressService service) {
		this.service = service;
	}
	
	@PostMapping(path = "/create", consumes={"application/json"}) 
	public ResponseEntity<?> create(@RequestBody AddressDTO address) {
		try {
			return ResponseEntity.ok().body(this.service.insert(address));
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping(path = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes={"application/json"})
	public ResponseEntity<?> update(@RequestBody AddressDTO address, @PathVariable Long id) {
		try {
			return ResponseEntity.ok().body(this.service.update(address, id));
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(path = "find/{id}", produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<?> findById(@PathVariable Long id) {
		try {
			return ResponseEntity.ok().body(this.service.findById(id));
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(path = "findAll", produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<?> findAll() {
		try {
			return ResponseEntity.ok().body(this.service.findAll());
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping(path = "delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> delete(@PathVariable Long id) {
		if(this.service.deleteById(id)) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
