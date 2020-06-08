package com.eagle.relationaldbaccessapi.services.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eagle.relationaldbaccessapi.models.dto.EmployeeDTO;
import com.eagle.relationaldbaccessapi.models.entity.EmployeeEntity;
import com.eagle.relationaldbaccessapi.repository.EmployeeRepocitory;
import com.eagle.relationaldbaccessapi.services.interfaces.IEmployeeService;
import com.eagle.relationaldbaccessapi.util.util.EmployeeUtil;
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
			EmployeeEntity employeeToInsert = new EmployeeEntity(dto);
			response = this.repocitory.save(employeeToInsert);
			LOGGER.info("Inserted {} ", dto);
		} catch (Exception e) {
			LOGGER.error("Error to insert Employe: ", e);
		}
		return new EmployeeDTO(response);
	}

	@Override
	public EmployeeDTO update(EmployeeDTO dto, Long id) {
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public EmployeeDTO findById(Long id) {
		if(this.repocitory.existsById(id)) {
			EmployeeEntity entity = this.repocitory.findById(id).get();
			return EmployeeUtil.buildEployeeDTO.build(entity);
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

}
