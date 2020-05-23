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
	
	public static class Builder {
		
		private AddressEntity addressEntity;
		
		public Builder() {
			this.addressEntity = new AddressEntity();
		}
		
		public Builder addId(Long id) {
			this.addressEntity.setId(id);
			return this;
		}
		
		public Builder addStreet(String street) {
			this.addressEntity.setStreet(street);
			return this;
		}
		
		public Builder addColony(String colony) {
			this.addressEntity.setColony(colony);
			return this;
		}
		
		public Builder addTownHall(String townHall) {
			this.addressEntity.setTownHall(townHall);;
			return this;
		}
		
		public Builder addEstate(String estate) {
			this.addressEntity.setEstate(estate);
			return this;
		}
		
		public Builder addInternalNumber(Integer internalNumber) {
			this.addressEntity.setInternalNumber(internalNumber);;
			return this;
		}
		
		public Builder addExternalNumber(Integer externalNumber) {
			this.addressEntity.setExternalNumber(externalNumber);;
			return this;
		}
		
		public Builder addLat(BigDecimal lat) {
			this.addressEntity.setLat(lat);
			return this;
		}
		
		public Builder addLon(BigDecimal lon) {
			this.addressEntity.setLon(lon);
			return this;
		}
		
		public AddressEntity build() {
			return this.addressEntity;
		}
	}
}
