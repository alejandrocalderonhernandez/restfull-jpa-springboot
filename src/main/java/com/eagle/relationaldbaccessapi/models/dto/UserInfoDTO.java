package com.eagle.relationaldbaccessapi.models.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.eagle.relationaldbaccessapi.models.entity.UserInfoEntity;
import com.google.gson.Gson;

public class UserInfoDTO implements Serializable{

	private static final long serialVersionUID = -8096606183270497702L;
	
	private Long id;
	@NotNull
	@Size(min = 3,max = 20)
	private String name1;
	@Size(min = 3,max = 20)
	private String name2;
	@NotNull
	private String lastName1;
	@Size(min = 3,max = 20)
	private String lastName2;
	@Size(min = 18, max = 18)
	private String curp;
	private String photoUrl;
	private LocalDateTime createAt;
	private Short age;
	private Boolean status;
	private EmployeeDTO employee;
	
    public UserInfoDTO() {
    }
    
    public UserInfoDTO(UserInfoEntity userInfo) {
    	this.setId(userInfo.getId());
    	this.setName1(userInfo.getName1());
    	this.setName2(userInfo.getName2());
    	this.setLastName1(userInfo.getLastName1());
    	this.setLastName2(userInfo.getLastName2());
    	this.setCurp(userInfo.getCurp());
    	this.setPhotoUrl(userInfo.getPhotoUrl());
    	this.setCreateAt(userInfo.getCreateAt());
    	this.setAge(userInfo.getAge());
    	this.setStatus(userInfo.getStatus());
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName1() {
		return name1;
	}

	public void setName1(String name1) {
		this.name1 = name1;
	}

	public String getName2() {
		return name2;
	}

	public void setName2(String name2) {
		this.name2 = name2;
	}

	public String getLastName1() {
		return lastName1;
	}

	public void setLastName1(String lastName1) {
		this.lastName1 = lastName1;
	}

	public String getLastName2() {
		return lastName2;
	}

	public void setLastName2(String lastName2) {
		this.lastName2 = lastName2;
	}

	public String getCurp() {
		return curp;
	}

	public void setCurp(String curp) {
		this.curp = curp;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public LocalDateTime getCreateAt() {
		return createAt;
	}

	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}

	public Short getAge() {
		return age;
	}

	public void setAge(Short age) {
		this.age = age;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
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
