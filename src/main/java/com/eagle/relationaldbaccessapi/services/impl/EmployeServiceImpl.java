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
import com.eagle.relationaldbaccessapi.repository.EmployeeRepository;
import com.eagle.relationaldbaccessapi.services.interfaces.IEmployeeService;
import com.eagle.relationaldbaccessapi.util.interfaces.functional.IUpdater;
import com.eagle.relationaldbaccessapi.util.strategies.BuilderDTOSWithRelationStrategies;
import com.eagle.relationaldbaccessapi.util.strategies.BuilderEntityWithRelationStrategies;
import com.eagle.relationaldbaccessapi.util.strategies.BuilderSimpleDTOStrategies;
import com.eagle.relationaldbaccessapi.util.strategies.BuilderSimpleEntityStrategies;
import com.eagle.relationaldbaccessapi.util.util.StringUtil;

@Service
public class EmployeServiceImpl implements IEmployeeService {
	
	private static final String TYPE = "Employee";
    private static final Logger LOGGER = LogManager.getLogger(EmployeServiceImpl.class);
    
    private IUpdater<EmployeeDTO, EmployeeEntity> updater = (newEmployee, oldEmployee) -> {
    	oldEmployee.setAlternativeId(newEmployee.getAlternativeId());
    	if(newEmployee.getAddress()!=null) {
        	oldEmployee.setAddress(BuilderSimpleEntityStrategies.BUILD_ADDRESS_ENTITY.build(newEmployee.getAddress()));
    	}
    	if(newEmployee.getUserInfo() != null) {
    		oldEmployee.setUserInfo(BuilderSimpleEntityStrategies.BUILD_USER_INFO_ENTITY.build(newEmployee.getUserInfo()));
    	}
    	if(newEmployee.getContact() != null) {
    		oldEmployee.setContact(BuilderSimpleEntityStrategies.BUILD_CONTACT_ENTITY.build(newEmployee.getContact()));
    	}

    };
	
	private EmployeeRepository repocitory;
	
	@Autowired
	public EmployeServiceImpl(EmployeeRepository repocitory) {
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
		Optional<EmployeeEntity> response = this.repocitory.findById(id);
		if (response.isPresent()) {
			try {
				EmployeeEntity employeeToUpdate = response.get();
				this.updater.update(dto, employeeToUpdate);
				this.repocitory.save(employeeToUpdate);
				LOGGER.info("Updated {} ", dto);
				return selectBuilderDTO(employeeToUpdate);
			} catch (Exception e) {
				LOGGER.error("Error to update Employee: ", e);
				return null;
			}
		} else {
			LOGGER.warn("Update Eployee not found id: " + id);
			throw new IllegalArgumentException(StringUtil.badIdMessage(TYPE, id));
		}
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
