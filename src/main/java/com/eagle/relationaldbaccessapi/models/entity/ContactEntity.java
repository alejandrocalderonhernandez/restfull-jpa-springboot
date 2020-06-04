package com.eagle.relationaldbaccessapi.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.eagle.relationaldbaccessapi.models.dto.ContactDTO;

@Entity
@Table(name = "CONTACT_INFO")
public class ContactEntity implements Serializable{

	private static final long serialVersionUID = -5651875879254986444L;
	
	@Id
	@Column(name = "id", columnDefinition = "bigserial")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "phone_number", length = 14, nullable = true)
	private String phoneNumber;
	@Column(name = "work_number", length = 14, nullable = true)
	private String workNumber;
	@Column(name = "home_number", length = 12, nullable = true)
	private String homeNumber;
	@Column(name = "email", length = 30, nullable = true)
	private String email;
	
	@OneToOne(mappedBy = "contact", fetch = FetchType.LAZY)
	private EmployeeEntity employee;
	
	public ContactEntity() {
	}
	
	public ContactEntity(ContactDTO contact) {
		this.setPhoneNumber(contact.getPhoneNumber());
		this.setWorkNumber(contact.getWorkNumber());
		this.setHomeNumber(contact.getHomeNumber());
		this.setEmail(contact.getEmail());
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

	public EmployeeEntity getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeEntity employee) {
		this.employee = employee;
	}

}
