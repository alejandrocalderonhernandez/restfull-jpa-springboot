package com.eagle.relationaldbaccessapi.models.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

import com.eagle.relationaldbaccessapi.models.entity.ContactEntity;
import com.google.gson.Gson;

import static com.eagle.relationaldbaccessapi.util.constants.RegexConstants.ONLY_NUMBERS_REGEX;

public class ContactDTO implements Serializable{

	private static final long serialVersionUID = 1615125051548395738L;

	private Long id;
	@Pattern(regexp = ONLY_NUMBERS_REGEX)
	private String phoneNumber;
	@Pattern(regexp = ONLY_NUMBERS_REGEX)
	private String workNumber;
	@Pattern(regexp = ONLY_NUMBERS_REGEX)
	private String homeNumber;
	@Email
	private String email;
	private EmployeeDTO employee;
	
	public ContactDTO() {
	}
	
	public ContactDTO(ContactEntity contact) {
		this.setId(contact.getId());
		this.setPhoneNumber(contact.getPhoneNumber());
		this.setWorkNumber(contact.getWorkNumber());
		this.setHomeNumber(contact.getHomeNumber());
		this.setEmail(contact.getEmail());
    	if (contact.getEmployee() != null) {
    		this.setEmployee(new EmployeeDTO(contact.getEmployee()));
    	}
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getWorkNumber() {
		return workNumber;
	}
	
	public void setWorkNumber(String workNumber) {
		this.workNumber = workNumber;
	}
	
	public String getHomeNumber() {
		return homeNumber;
	}
	
	public void setHomeNumber(String homeNumber) {
		this.homeNumber = homeNumber;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public EmployeeDTO getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeDTO employee) {
		this.employee = employee;
	}
	
	@Override
	public String toString() {
		return new Gson().toJson(this);
	}

}
