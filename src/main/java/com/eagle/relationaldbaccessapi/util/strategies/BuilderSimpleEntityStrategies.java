package com.eagle.relationaldbaccessapi.util.strategies;

import java.time.LocalDateTime;

import com.eagle.relationaldbaccessapi.models.dto.AddressDTO;
import com.eagle.relationaldbaccessapi.models.dto.ContactDTO;
import com.eagle.relationaldbaccessapi.models.dto.EmployeeDTO;
import com.eagle.relationaldbaccessapi.models.dto.UserInfoDTO;
import com.eagle.relationaldbaccessapi.models.entity.AddressEntity;
import com.eagle.relationaldbaccessapi.models.entity.ContactEntity;
import com.eagle.relationaldbaccessapi.models.entity.EmployeeEntity;
import com.eagle.relationaldbaccessapi.models.entity.UserInfoEntity;
import com.eagle.relationaldbaccessapi.util.interfaces.functional.IBuilder;

public class BuilderSimpleEntityStrategies {
	
	private BuilderSimpleEntityStrategies() {
	}
	
	public static  final IBuilder<AddressEntity, AddressDTO> BUILD_ADDRESS_ENTITY = (dto) -> {
		AddressEntity entity = new AddressEntity();
		entity.setStreet(dto.getStreet());
		entity.setColony(dto.getColony());
		entity.setTownHall(dto.getTownHall());
		entity.setEstate(dto.getEstate());
		entity.setInternalNumber(dto.getInternalNumber());
		entity.setExternalNumber(dto.getExternalNumber());
		entity.setLat(dto.getLat());
		entity.setLon(dto.getLon());
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
    	entity.setCreateAt(LocalDateTime.now());
    	entity.setAge(dto.getAge());
    	entity.setStatus(true);
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
		if (dto.getUserInfo()!=null) {
			entity.setUserInfo(BUILD_USER_INFO_ENTITY.build(dto.getUserInfo()));
		}
		return entity;
	};

}
