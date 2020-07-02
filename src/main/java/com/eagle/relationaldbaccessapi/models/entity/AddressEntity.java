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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
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

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
	
}
