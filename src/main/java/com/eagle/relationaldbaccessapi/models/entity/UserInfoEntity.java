package com.eagle.relationaldbaccessapi.models.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.eagle.relationaldbaccessapi.models.dto.UserInfoDTO;

@Entity
@Table(name = "USER_INFO")
public class UserInfoEntity implements Serializable{

	private static final long serialVersionUID = -6742470073798537394L;
	
	@Id
	@Column(name = "id", columnDefinition = "bigserial")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name_1", length = 20, nullable = false)
	private String name1;
	
	@Column(name = "name_2", length = 20, nullable = true)
	private String name2;
	@Column(name = "lastname_1", length = 20, nullable = false)
	private String lastName1;
	@Column(name = "lastname_2", length = 20, nullable = true)
	private String lastName2;
	@Column(name = "curp", length = 18, nullable = true)
	private String curp;
	@Column(name = "photo", length = 100, nullable = true)
	private String photoUrl;
	@Column(name = "created_at", nullable = false)
	private LocalDateTime createAt;
	@Column(name = "years_age", nullable = true)
	private Short age;
	@Column(name = "status", nullable = false)
	private Boolean status;
	
	@OneToOne(mappedBy = "userInfo", fetch = FetchType.LAZY)
	private EmployeeEntity employee;

	public UserInfoEntity() {
	}
	
    public UserInfoEntity(UserInfoDTO userInfo) {
    	this.setName1(userInfo.getName1());
    	this.setName2(userInfo.getName2());
    	this.setLastName1(userInfo.getLastName1());
    	this.setLastName2(userInfo.getLastName2());
    	this.setCurp(userInfo.getCurp());
    	this.setPhotoUrl(userInfo.getPhotoUrl());
    	this.setCreateAt(LocalDateTime.now());
    	this.setAge(userInfo.getAge());
    	this.setStatus(true);
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

	public EmployeeEntity getEmployee() {
		return employee;
	}

	public void setEmploye(EmployeeEntity employee) {
		this.employee = employee;
	}

	@Override
	public String toString() {
		return "UserInfoEntity [id=" + id + ", name1=" + name1 + ", name2=" + name2 + ", lastName1=" + lastName1
				+ ", lastName2=" + lastName2 + ", curp=" + curp + ", photoUrl=" + photoUrl + ", createAt=" + createAt
				+ ", age=" + age + ", status=" + status + ", employee=" + employee + "]";
	}
	
}
