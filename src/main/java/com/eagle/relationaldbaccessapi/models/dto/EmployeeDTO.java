package com.eagle.relationaldbaccessapi.models.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeDTO implements Serializable {
	
	private static final long serialVersionUID = -3446998278502425285L;
	
	private Long id;
	private String alternativeId;
	@NotNull
	private UserInfoDTO userInfo;
	private AddressDTO address;
	private ContactDTO contact;
	
	@Override
	public String toString() {
		return "EmployeeDTO: ".concat(new Gson().toJson(this));
	}

}
