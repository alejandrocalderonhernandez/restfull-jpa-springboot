package com.eagle.relationaldbaccessapi.models.dto;

import java.io.Serializable;
import java.util.Set;

import com.google.gson.Gson;

public class RouteDTO implements Serializable {
	
	private static final long serialVersionUID = 7733695206854924131L;
	
	private Long id;
	private String alternativeId;
	private String name;
	private Boolean directTravel;
	private OriginDTO origin;
	private Set<DestinationDTO> destinations;
	
	public RouteDTO() {
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
	
	public Boolean isDirectTravel() {
		return directTravel;
	}
	
	public void setDirectTravel(Boolean directTravel) {
		this.directTravel = directTravel;
	}
	
	public OriginDTO getOrigin() {
		return origin;
	}
	
	public void setOrigin(OriginDTO origin) {
		this.origin = origin;
	}
	
	public Set<DestinationDTO> getDestinations() {
		return destinations;
	}
	
	public void setDestinations(Set<DestinationDTO> destinations) {
		this.destinations = destinations;
	}
	
	@Override
	public String toString() {
		return "DestinationDTO: ".concat(new Gson().toJson(this));
	}

}
