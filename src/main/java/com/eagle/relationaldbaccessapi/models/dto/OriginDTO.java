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
public class OriginDTO implements Serializable{
	
	private static final long serialVersionUID = 8329293527054247060L;
	
	private Long id;
	private String alternativeId;
	private String name;
	private Short type;
	
	private AddressDTO address;
	
	private Set<RouteDTO> routes;

	@Override
	public String toString() {
		return "OriginDTO: ".concat(new Gson().toJson(this));
	}

}
