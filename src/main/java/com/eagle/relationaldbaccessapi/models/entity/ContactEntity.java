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
import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
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
	
	public ContactEntity(ContactDTO contact) {
		this.setPhoneNumber(contact.getPhoneNumber());
		this.setWorkNumber(contact.getWorkNumber());
		this.setHomeNumber(contact.getHomeNumber());
		this.setEmail(contact.getEmail());
	}

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}

}
