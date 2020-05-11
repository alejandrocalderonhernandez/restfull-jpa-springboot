package com.eagle.relationaldbaccessapi.models.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ADDRESS")
public class AddressEntity implements Serializable{
	
	private static final long serialVersionUID = -4055365928482818390L;
	
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
	
	public AddressEntity() {
	}

	public AddressEntity(Long id, String street, String colony, String townHall, String estate, Integer externalNumber,
			Integer internalNumber, BigDecimal lat, BigDecimal lon) {
		this.id = id;
		this.street = street;
		this.colony = colony;
		this.townHall = townHall;
		this.estate = estate;
		this.externalNumber = externalNumber;
		this.internalNumber = internalNumber;
		this.lat = lat;
		this.lon = lon;
	}
	
	public AddressEntity(String street, String colony, String townHall, String estate, Integer externalNumber,
			Integer internalNumber, BigDecimal lat, BigDecimal lon) {
		this.street = street;
		this.colony = colony;
		this.townHall = townHall;
		this.estate = estate;
		this.externalNumber = externalNumber;
		this.internalNumber = internalNumber;
		this.lat = lat;
		this.lon = lon;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((colony == null) ? 0 : colony.hashCode());
		result = prime * result + ((estate == null) ? 0 : estate.hashCode());
		result = prime * result + ((externalNumber == null) ? 0 : externalNumber.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((internalNumber == null) ? 0 : internalNumber.hashCode());
		result = prime * result + ((lat == null) ? 0 : lat.hashCode());
		result = prime * result + ((lon == null) ? 0 : lon.hashCode());
		result = prime * result + ((street == null) ? 0 : street.hashCode());
		result = prime * result + ((townHall == null) ? 0 : townHall.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AddressEntity other = (AddressEntity) obj;
		if (colony == null) {
			if (other.colony != null)
				return false;
		} else if (!colony.equals(other.colony))
			return false;
		if (estate == null) {
			if (other.estate != null)
				return false;
		} else if (!estate.equals(other.estate))
			return false;
		if (externalNumber == null) {
			if (other.externalNumber != null)
				return false;
		} else if (!externalNumber.equals(other.externalNumber))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (internalNumber == null) {
			if (other.internalNumber != null)
				return false;
		} else if (!internalNumber.equals(other.internalNumber))
			return false;
		if (lat == null) {
			if (other.lat != null)
				return false;
		} else if (!lat.equals(other.lat))
			return false;
		if (lon == null) {
			if (other.lon != null)
				return false;
		} else if (!lon.equals(other.lon))
			return false;
		if (street == null) {
			if (other.street != null)
				return false;
		} else if (!street.equals(other.street))
			return false;
		if (townHall == null) {
			if (other.townHall != null)
				return false;
		} else if (!townHall.equals(other.townHall))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AddressEntity [id=" + id + ", street=" + street + ", colony=" + colony + ", townHall=" + townHall
				+ ", estate=" + estate + ", externalNumber=" + externalNumber + ", internalNumber=" + internalNumber
				+ ", lat=" + lat + ", lon=" + lon + "]";
	}
}
