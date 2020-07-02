package com.eagle.relationaldbaccessapi.models.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddressDTO implements Serializable {
	
	private static final long serialVersionUID = 4055365928482918390L;
	
	private Long id;
	@NotNull
	@Size(min = 1, max = 30)
	private String street;
	@NotNull
	@Size(min = 3, max = 30)
	private String colony;
	@Size(min = 3, max = 30)
	private String townHall;
	@NotNull
	@Size(min = 3, max = 30)
	private String estate;
	private Integer externalNumber;
	private Integer internalNumber;
	private BigDecimal lat;
	private BigDecimal lon;
	private EmployeeDTO employee;


	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
	
}
