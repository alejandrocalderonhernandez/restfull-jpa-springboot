package com.eagle.relationaldbaccessapi.services.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.eagle.relationaldbaccessapi.models.dto.RouteDTO;
import com.eagle.relationaldbaccessapi.models.entity.RouteEntity;
import com.eagle.relationaldbaccessapi.repository.RouteRepository;
import com.eagle.relationaldbaccessapi.services.interfaces.IRouteService;
import com.eagle.relationaldbaccessapi.util.strategies.BuilderDTOSWithRelationStrategies;
import com.eagle.relationaldbaccessapi.util.strategies.BuilderEntityWithRelationStrategies;

@Service
public class RouteServiceImpl implements IRouteService{
	
    private static final Logger LOGGER = LogManager.getLogger(RouteServiceImpl.class);
    
    private RouteRepository repocitory;
    
    @Autowired
   public RouteServiceImpl(RouteRepository repocitory) {
	this.repocitory = repocitory;
}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public RouteDTO insert(RouteDTO dto) {
		RouteDTO response = null;
		try {
			RouteEntity routeToInsert = BuilderEntityWithRelationStrategies.BUILDER_ROUTE_ENTITY.build(dto);
			routeToInsert.getOrigin().addRoute(routeToInsert);
			LOGGER.info("Inserted {} ", dto);
			response = BuilderDTOSWithRelationStrategies.BUILDER_ROUTE_DTO.build(this.repocitory.save(routeToInsert));
		} catch (Exception e) {
			LOGGER.error("Error to insert Route: ", e);
		}
		return response;
	}

	@Override
	public RouteDTO update(RouteDTO dto, Long id) {
		return null;
	}

	@Override
	public RouteDTO findById(Long id) {
		return null;
	}

	@Override
	public List<RouteDTO> findAll() {
		return null;
	}

	@Override
	public void deleteById(Long id) {
		
	}

}
