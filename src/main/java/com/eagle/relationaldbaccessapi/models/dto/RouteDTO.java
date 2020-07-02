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
public class RouteDTO implements Serializable {
	
	private static final long serialVersionUID = 7733695206854924131L;
	
	private Long id;
	private String alternativeId;
	private String name;
	private Boolean directTravel;
	private OriginDTO origin;
	private Set<DestinationDTO> destinations;
	
	@Override
	public String toString() {
		return "DestinationDTO: ".concat(new Gson().toJson(this));
	}

}
