package com.eagle.relationaldbaccessapi.services.interfaces;

import com.eagle.relationaldbaccessapi.models.dto.RouteDTO;
import com.eagle.relationaldbaccessapi.services.interfaces.common.ISimpleCrud;

public interface IRouteService extends ISimpleCrud<RouteDTO> {
	
	  static final String TYPE = "Route";
}
