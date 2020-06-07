package com.eagle.relationaldbaccessapi.models.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.eagle.relationaldbaccessapi.models.entity.AddressEntity;
import com.google.gson.Gson;

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
	
	public AddressDTO() {
	}

	public AddressDTO(AddressEntity address) {
		 this.setId(address.getId());
		 this.setStreet(address.getStreet());
		 this.setColony(address.getColony());
		 this.setTownHall(address.getTownHall());
		 this.setEstate(address.getEstate());
		 this.setInternalNumber(address.getInternalNumber());
		 this.setExternalNumber(address.getExternalNumber());
		 this.setLat(address.getLat());
		 this.setLon(address.getLon());
	    if (address.getEmployee() != null) {
	   		this.setEmployee(new EmployeeDTO(address.getEmployee()));
	   	}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getColony() {
		return colony;
	}

	public void setColony(String colony) {
		this.colony = colony;
	}

	public String getTownHall() {
		return townHall;
	}

	public void setTownHall(String townHall) {
		this.townHall = townHall;
	}

	public String getEstate() {
		return estate;
	}

	public void setEstate(String estate) {
		this.estate = estate;
	}

	public Integer getExternalNumber() {
		return externalNumber;
	}

	public void setExternalNumber(Integer externalNumber) {
		this.externalNumber = externalNumber;
	}

	public Integer getInternalNumber() {
		return internalNumber;
	}

	public void setInternalNumber(Integer internalNumber) {
		this.internalNumber = internalNumber;
	}

	public BigDecimal getLat() {
		return lat;
	}

	public void setLat(BigDecimal lat) {
		this.lat = lat;
	}

	public BigDecimal getLon() {
		return lon;
	}

	public void setLon(BigDecimal lon) {
		this.lon = lon;
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
