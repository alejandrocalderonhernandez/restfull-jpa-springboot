package com.eagle.relationaldbaccessapi.models.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class AddressDTO implements Serializable {
	
	private static final long serialVersionUID = 4055365928482918390L;
	
	private Long id;
	private String street;
	private String colony;
	private String townHall;
	private String estate;
	private Integer externalNumber;
	private Integer internalNumber;
	private BigDecimal lat;
	private BigDecimal lon;
	
	public AddressDTO() {
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
	
	public static class Builder {
		
		private AddressDTO addressDTO;
		
		public Builder() {
			this.addressDTO = new AddressDTO();
		}
		
		public Builder addId(Long id) {
			this.addressDTO.setId(id);
			return this;
		}
		
		public Builder addStreet(String street) {
			this.addressDTO.setStreet(street);
			return this;
		}
		
		public Builder addColony(String colony) {
			this.addressDTO.setColony(colony);
			return this;
		}
		
		public Builder addTownHall(String townHall) {
			this.addressDTO.setTownHall(townHall);;
			return this;
		}
		
		public Builder addEstate(String estate) {
			this.addressDTO.setEstate(estate);
			return this;
		}
		
		public Builder addInternalNumber(Integer internalNumber) {
			this.addressDTO.setInternalNumber(internalNumber);;
			return this;
		}
		
		public Builder addExternalNumber(Integer externalNumber) {
			this.addressDTO.setExternalNumber(externalNumber);;
			return this;
		}
		
		public Builder addLat(BigDecimal lat) {
			this.addressDTO.setLat(lat);
			return this;
		}
		
		public Builder addLon(BigDecimal lon) {
			this.addressDTO.setLon(lon);
			return this;
		}
		
		public AddressDTO build() {
			return this.addressDTO;
		}
	}
}
