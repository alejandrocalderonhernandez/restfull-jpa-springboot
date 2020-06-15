package com.eagle.relationaldbaccessapi.models.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.google.gson.Gson;

public class EmployeeDTO implements Serializable {
	
	private static final long serialVersionUID = -3446998278502425285L;
	
	private Long id;
	private String alternativeId;
	@	NotNull
	private UserInfoDTO userInfo;
	private AddressDTO address;
	private ContactDTO contact;
	
	public EmployeeDTO() {
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAlternativeId() {
		return alternativeId;
	}

	public void setAlternativeId(String alternativeId) {
		this.alternativeId = alternativeId;
	}

	public UserInfoDTO getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfoDTO userInfo) {
		this.userInfo = userInfo;
	}

	public AddressDTO getAddress() {
		return address;
	}

	public void setAddress(AddressDTO address) {
		this.address = address;
	}

	public ContactDTO getContact() {
		return contact;
	}

	public void setContact(ContactDTO contact) {
		this.contact = contact;
	}
	
	@Override
	public String toString() {
		return "EmployeeDTO: ".concat(new Gson().toJson(this));
	}

}
