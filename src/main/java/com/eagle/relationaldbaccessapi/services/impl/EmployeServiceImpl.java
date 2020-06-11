package com.eagle.relationaldbaccessapi.services.impl;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eagle.relationaldbaccessapi.models.dto.EmployeeDTO;
import com.eagle.relationaldbaccessapi.models.entity.EmployeeEntity;
import com.eagle.relationaldbaccessapi.repository.EmployeeRepocitory;
import com.eagle.relationaldbaccessapi.services.interfaces.IEmployeeService;
import com.eagle.relationaldbaccessapi.util.strategies.BuilderDTOSWithRelationStrategies;
import com.eagle.relationaldbaccessapi.util.strategies.BuilderEntityWithRelationStrategies;
import com.eagle.relationaldbaccessapi.util.strategies.BuilderSimpleDTOStrategies;
import com.eagle.relationaldbaccessapi.util.strategies.BuilderSimpleEntityStrategies;
import com.eagle.relationaldbaccessapi.util.util.StringUtil;

@Service
public class EmployeServiceImpl implements IEmployeeService {
	
	private static final String TYPE = "Employee";
    private static final Logger LOGGER = LogManager.getLogger(EmployeServiceImpl.class);
	
	private EmployeeRepocitory repocitory;
	
	@Autowired
	public EmployeServiceImpl(EmployeeRepocitory repocitory) {
		this.repocitory = repocitory;
	}

	@Override
	@Transactional
	public EmployeeDTO insert(EmployeeDTO dto) {
		EmployeeEntity response = null;
		try {
			EmployeeEntity employeeToInsert = this.selectBuilderEntity(dto);
			response = this.repocitory.save(employeeToInsert);
			LOGGER.info("Inserted {} ", dto);
		} catch (Exception e) {
			LOGGER.error("Error to insert Employe: ", e);
		}
		return this.selectBuilderDTO(response);
	}

	@Override
	public EmployeeDTO update(EmployeeDTO dto, Long id) {
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public EmployeeDTO findById(Long id) {
		Optional<EmployeeEntity> responce = repocitory.findById(id);
		if(responce.isPresent()) {
			return BuilderDTOSWithRelationStrategies.BUILD_EMPLOYEE_DTO.build(responce.get());
		} else {
			 LOGGER.warn("Select Eployee not found id: " + id);
			throw new IllegalArgumentException(StringUtil.badIdMessage(TYPE, id));
		}
	}

	@Override
	public List<EmployeeDTO> findAll() {
		return null;
	}

	@Override
	public void deleteById(Long id) {
	
	}

	@Override
	public EmployeeDTO findByAlternativeId(String id) {
		Optional<EmployeeEntity> responce = this.repocitory.findByAlternativeId(id);
		if(responce.isPresent()) {
			return BuilderDTOSWithRelationStrategies.BUILD_EMPLOYEE_DTO.build(responce.get());
		} else {
			 LOGGER.warn("Select Eployee not found id: " + id);
			throw new IllegalArgumentException(StringUtil.badIdMessage(TYPE, id));
		}
	}
	
	private EmployeeEntity selectBuilderEntity(final EmployeeDTO dto) {
		if (dto.getAddress() != null && dto.getContact() != null) {
			return BuilderEntityWithRelationStrategies.BUILD_EMPLOYEE_ENTITY.build(dto);
		} else {
			return BuilderSimpleEntityStrategies.BUILD_EMPLOYEE_ENTITY.build(dto);
		}
	}
	
	private EmployeeDTO selectBuilderDTO(final EmployeeEntity entity) {
		if ((entity.getAddress() != null && entity.getContact() != null)) {
			return BuilderDTOSWithRelationStrategies.BUILD_EMPLOYEE_DTO.build(entity);
		} else {
			return BuilderSimpleDTOStrategies.BUILD_EMPLOYEE_DTO.build(entity);
		}
	}

}
