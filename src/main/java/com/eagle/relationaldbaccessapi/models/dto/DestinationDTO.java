package com.eagle.relationaldbaccessapi.models.dto;

import java.io.Serializable;
import java.util.Set;

import com.google.gson.Gson;

public class DestinationDTO implements Serializable{

	private static final long serialVersionUID = -6245950174362645499L;

	private Long id;
	private String alternativeId;
	private String name;
	private 	Short type;
	private Boolean finalDestination;
	
	private AddressDTO addess;
	
	private Set<RouteDTO> routes;
	
	public DestinationDTO() {
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
	
	public Boolean isFinalDestination() {
		return finalDestination;
	}
	
	public void setFinalDestination(Boolean finalDestination) {
		this.finalDestination = finalDestination;
	}
	
	public AddressDTO getAddess() {
		return addess;
	}
	
	public void setAddess(AddressDTO addess) {
		this.addess = addess;
	}
	
	public Set<RouteDTO> getRoutes() {
		return routes;
	}
	
	public void setRoutes(Set<RouteDTO> routes) {
		this.routes = routes;
	}
	
	@Override
	public String toString() {
		return "DestinationEntity: ".concat(new Gson().toJson(this));
	}
}
