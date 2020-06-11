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

public class BuilderEntityWithRelationStrategies {
	
	
	private BuilderEntityWithRelationStrategies() {
	}
	
	public static  final IBuilder<AddressEntity, AddressDTO> BUILD_ADDRESS_ENTITY_WITH_EMPLOYEE = (dto) -> {
		AddressEntity entity = new AddressEntity();
		entity.setStreet(dto.getStreet());
		entity.setColony(dto.getColony());
		entity.setTownHall(dto.getTownHall());
		entity.setEstate(dto.getEstate());
		entity.setInternalNumber(dto.getInternalNumber());
		entity.setExternalNumber(dto.getExternalNumber());
		entity.setLat(dto.getLat());
		entity.setLon(dto.getLon());
		if(dto.getEmployee() != null) {
			entity.setEmploye(BuilderSimpleEntityStrategies.BUILD_EMPLOYEE_ENTITY.build(dto.getEmployee()));
		}
		return entity;
	};
	
	public static final IBuilder<UserInfoEntity, UserInfoDTO> BUILD_USER_INFO_ENTITY = (dto) -> {
		UserInfoEntity entity = new UserInfoEntity();
    	entity.setName1(dto.getName1());
    	entity.setName2(dto.getName2());
    	entity.setLastName1(dto.getLastName1());
    	entity.setLastName2(dto.getLastName2());
    	entity.setCurp(dto.getCurp());
    	entity.setPhotoUrl(dto.getPhotoUrl());
    	entity.setCreateAt(dto.getCreateAt());
    	entity.setAge(dto.getAge());
    	entity.setStatus(dto.getStatus());
		return entity;
	};
	
	public static  final IBuilder<ContactEntity, ContactDTO> BUILD_CONTACT_ENTITY = (dto) -> {
		ContactEntity entity = new ContactEntity();
		entity.setPhoneNumber(dto.getPhoneNumber());
		entity.setWorkNumber(dto.getWorkNumber());
		entity.setHomeNumber(dto.getHomeNumber());
		entity.setEmail(dto.getEmail());
		return entity;
	};
	
	public static final IBuilder<EmployeeEntity, EmployeeDTO> BUILD_EMPLOYEE_ENTITY = (dto) -> {
		EmployeeEntity entity = new EmployeeEntity();
		entity.setAlternativeId(dto.getAlternativeId());
		entity.setUserInfo(BuilderSimpleEntityStrategies.BUILD_USER_INFO_ENTITY.build(dto.getUserInfo()));
		if(dto.getAddress() != null) {
			entity.setAddress(BuilderSimpleEntityStrategies.BUILD_ADDRESS_ENTITY.build(dto.getAddress()));
		}
		if(dto.getContact() != null) {
			entity.setContact(BuilderSimpleEntityStrategies.BUILD_CONTACT_ENTITY.build(dto.getContact()));
		}
		return entity;
	};
	
	

}
