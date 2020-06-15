package com.eagle.relationaldbaccessapi.models.dto;

import java.io.Serializable;
import java.util.Set;

import com.google.gson.Gson;

public class OriginDTO implements Serializable{
	
	private static final long serialVersionUID = 8329293527054247060L;
	
	private Long id;
	private String alternativeId;
	private String name;
	private 	Short type;
	
	private AddressDTO address;
	
	private Set<RouteDTO> routes;
	
	public OriginDTO() {
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

	public AddressDTO getAddress() {
		return address;
	}

	public void setAddress(AddressDTO address) {
		this.address = address;
	}

	public Set<RouteDTO> getRoutes() {
		return routes;
	}

	public void setRoutes(Set<RouteDTO> routes) {
		this.routes = routes;
	}

	@Override
	public String toString() {
		return "OriginDTO: ".concat(new Gson().toJson(this));
	}

}
