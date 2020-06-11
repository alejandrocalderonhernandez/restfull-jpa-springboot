package com.eagle.relationaldbaccessapi.services.interfaces;

import com.eagle.relationaldbaccessapi.models.dto.EmployeeDTO;
import com.eagle.relationaldbaccessapi.services.interfaces.common.ISimpleCrud;

public interface IEmployeeService extends ISimpleCrud<EmployeeDTO>{

	public EmployeeDTO findByAlternativeId(String id);
}
