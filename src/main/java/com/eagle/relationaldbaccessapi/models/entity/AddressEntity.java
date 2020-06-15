package com.eagle.relationaldbaccessapi.models.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.google.gson.Gson;

@Entity
@Table(name = "ADDRESS")
public class AddressEntity implements Serializable{

	private static final long serialVersionUID = 5688324605219835087L;
	
	@Id
	@Column(name = "id", columnDefinition = "bigserial")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "street", length = 30, nullable = false)
	private String street;
	@Column(name = "colony", length = 30, nullable = false)
	private String colony;
	@Column(name = "town_hall", length = 30, nullable = true)
	private String townHall;
	@Column(name = "estate", length = 30, nullable = false)
	private String estate;
	@Column(name = "external_number",  nullable = true)
	private Integer externalNumber;
	@Column(name = "internal_number",  nullable = true)
	private Integer internalNumber;
	@Column(name = "lat", nullable = true)
	private BigDecimal lat;
	@Column(name = "lon",  nullable = true)
	private BigDecimal lon;
	
	@OneToOne(mappedBy = "address", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private EmployeeEntity employee;
	
	@OneToOne(mappedBy = "address", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private OriginEntity originEntity;
	
	public AddressEntity() {
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

	public EmployeeEntity getEmployee() {
		return employee;
	}

	public void setEmploye(EmployeeEntity employee) {
		this.employee = employee;
	}	
	
	public OriginEntity getOriginEntity() {
		return originEntity;
	}

	public void setOriginEntity(OriginEntity originEntity) {
		this.originEntity = originEntity;
	}

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
	
}
