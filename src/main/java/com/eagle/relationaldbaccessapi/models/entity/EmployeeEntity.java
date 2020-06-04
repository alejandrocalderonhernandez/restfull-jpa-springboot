package com.eagle.relationaldbaccessapi.models.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.eagle.relationaldbaccessapi.models.dto.EmployeeDTO;

@Entity
@Table(name = "EMPLOYEE")
public class EmployeeEntity implements Serializable{
	
	private static final long serialVersionUID = 1752998996057021363L;
	
	@Id
	@Column(name = "id", columnDefinition = "bigserial")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	@Column(name = "id_alternative", length = 30, nullable = true)
	private String alternativeId;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_user_info")
	private UserInfoEntity userInfo;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_address")
	private AddressEntity address;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_contact")
	private ContactEntity contact;
	
	public EmployeeEntity() {
	}
	
	public EmployeeEntity(EmployeeDTO employee) {
		this.setAlternativeId(employee.getAlternativeId());
		if(employee.getUserInfo() != null ) {
			this.setUserInfo(new UserInfoEntity(employee.getUserInfo()));
		}
		if(employee.getAddress() != null ) {
			this.setAddress(new AddressEntity(employee.getAddress()));
		}
		if(employee.getContact() != null ) {
			this.setContact(new ContactEntity(employee.getContact()));
		}
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

	public UserInfoEntity getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfoEntity userInfo) {
		this.userInfo = userInfo;
	}

	public AddressEntity getAddress() {
		return address;
	}

	public void setAddress(AddressEntity address) {
		this.address = address;
	}

	public ContactEntity getContact() {
		return contact;
	}

	public void setContact(ContactEntity contact) {
		this.contact = contact;
	}

}
