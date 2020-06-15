package com.eagle.relationaldbaccessapi.util.strategies;

import com.eagle.relationaldbaccessapi.models.dto.AddressDTO;
import com.eagle.relationaldbaccessapi.models.dto.ContactDTO;
import com.eagle.relationaldbaccessapi.models.dto.EmployeeDTO;
import com.eagle.relationaldbaccessapi.models.dto.UserInfoDTO;
import com.eagle.relationaldbaccessapi.models.entity.AddressEntity;
import com.eagle.relationaldbaccessapi.models.entity.ContactEntity;
import com.eagle.relationaldbaccessapi.models.entity.EmployeeEntity;
import com.eagle.relationaldbaccessapi.models.entity.UserInfoEntity;
import com.eagle.relationaldbaccessapi.util.interfaces.functional.IBuilder;

public class BuilderSimpleDTOStrategies {
	
	private BuilderSimpleDTOStrategies() {
	}
	
	public static final IBuilder<UserInfoDTO, UserInfoEntity> BUILD_USER_INFO_DTO = (entity) -> {
		UserInfoDTO dto = new UserInfoDTO();
    	dto.setId(entity.getId());
    	dto.setName1(entity.getName1());
    	dto.setName2(entity.getName2());
    	dto.setLastName1(entity.getLastName1());
    	dto.setLastName2(entity.getLastName2());
    	dto.setCurp(entity.getCurp());
    	dto.setPhotoUrl(entity.getPhotoUrl());
    	dto.setCreateAt(entity.getCreateAt());
    	dto.setAge(entity.getAge());
    	dto.setStatus(entity.getStatus());
		return dto;
	};
	
	public static final IBuilder<AddressDTO, AddressEntity> BUILD_ADDRESS_DTO = (entity) -> {
		 AddressDTO dto = new AddressDTO();
		 dto.setId(entity.getId());
		 dto.setStreet(entity.getStreet());
		 dto.setColony(entity.getColony());
		 dto.setTownHall(entity.getTownHall());
		 dto.setEstate(entity.getEstate());
		 dto.setInternalNumber(entity.getInternalNumber());
		 dto.setExternalNumber(entity.getExternalNumber());
		 dto.setLat(entity.getLat());
		 dto.setLon(entity.getLon());
		 return dto;
	};
	
	public static  final IBuilder<ContactDTO, ContactEntity> BUILD_CONTACT_DTO = (entity) -> {
		ContactDTO dto = new ContactDTO();
		dto.setId(entity.getId());
		dto.setPhoneNumber(entity.getPhoneNumber());
		dto.setWorkNumber(entity.getWorkNumber());
		dto.setHomeNumber(entity.getHomeNumber());
		dto.setEmail(entity.getEmail());
		return dto;
	};
	
	public static final IBuilder<EmployeeDTO, EmployeeEntity> BUILD_EMPLOYEE_DTO = (entity) -> {
		EmployeeDTO dto = new EmployeeDTO();
		dto.setId(entity.getId());
		dto.setAlternativeId(entity.getAlternativeId());
		return dto;
	};
	
	
	
}
