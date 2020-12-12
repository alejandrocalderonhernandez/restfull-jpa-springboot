package com.eagle.relationaldbaccessapi.models.dto;

import java.io.Serializable;
import java.util.Set;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DestinationDTO implements Serializable{

	private static final long serialVersionUID = -6245950174362645499L;

	private Long id;
	private String alternativeId;
	private String name;
	private Short type;
	private Boolean finalDestination;
	
	private AddressDTO address;
	
	private Set<RouteDTO> routes;
	
	@Override
	public String toString() {
		return "DestinationEntity: ".concat(new Gson().toJson(this));
	}
}
