package com.eagle.relationaldbaccessapi.models.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.google.gson.Gson;

@Entity
@Table(name = "ORIGIN")
public class OriginEntity implements Serializable{

	private static final long serialVersionUID = 4754407946803307618L;
	
	@Id
	@Column(name = "id", columnDefinition = "bigserial")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "id_alternative", length = 30, nullable = true)
	private String alternativeId;
	@Column(name = "name_origin", length = 30, nullable = false)
	private String name;
	@Column(name = "type_origin", nullable = false)
	private 	Short type;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_address")
	private AddressEntity address;
	
	@OneToMany(
			mappedBy = "origin", 
			fetch = FetchType.LAZY, 
			cascade = CascadeType.ALL, 
			orphanRemoval = true)
	private Set<RouteEntity> routes;
	
	public OriginEntity() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAlternativeId() {
		return alternativeId;
	}

	public void setAlternativeId(String alternativeId) {
		this.alternativeId = alternativeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Short getType() {
		return type;
	}

	public void setType(Short type) {
		this.type = type;
	}

	public AddressEntity getAddress() {
		return address;
	}

	public void setAddress(AddressEntity address) {
		this.address = address;
	}

	public Set<RouteEntity> getRoutes() {
		return routes;
	}

	public void setRoutes(Set<RouteEntity> routes) {
		this.routes = routes;
	}

	@Override
	public String toString() {
		return "OriginEntity: ".concat(new Gson().toJson(this));
	}
	
}
