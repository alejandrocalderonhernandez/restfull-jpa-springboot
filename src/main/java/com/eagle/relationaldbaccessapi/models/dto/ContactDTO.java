package com.eagle.relationaldbaccessapi.models.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

import com.eagle.relationaldbaccessapi.models.entity.ContactEntity;
import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.eagle.relationaldbaccessapi.util.constants.RegexConstants.ONLY_NUMBERS_REGEX;

@Getter
@Setter
@NoArgsConstructor
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

	public ContactDTO(ContactEntity contact) {

	}
	
	@Override
	public String toString() {
		return new Gson().toJson(this);
	}

}
