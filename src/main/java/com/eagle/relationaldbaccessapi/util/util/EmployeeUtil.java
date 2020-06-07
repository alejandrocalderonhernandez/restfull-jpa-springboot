package com.eagle.relationaldbaccessapi.util.util;

import com.eagle.relationaldbaccessapi.models.dto.AddressDTO;
import com.eagle.relationaldbaccessapi.models.dto.ContactDTO;
import com.eagle.relationaldbaccessapi.models.dto.EmployeeDTO;
import com.eagle.relationaldbaccessapi.models.dto.UserInfoDTO;
import com.eagle.relationaldbaccessapi.models.entity.AddressEntity;
import com.eagle.relationaldbaccessapi.models.entity.ContactEntity;
import com.eagle.relationaldbaccessapi.models.entity.EmployeeEntity;
import com.eagle.relationaldbaccessapi.models.entity.UserInfoEntity;
import com.eagle.relationaldbaccessapi.util.interfaces.functional.IBuilder;

public class EmployeeUtil {
	
	private static  IBuilder<UserInfoDTO, UserInfoEntity> buildUserInfoDTO = (entity) -> {
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
	
	private static  IBuilder<AddressDTO, AddressEntity> buildAddressDTO = (entity) -> {
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
	
	private static  IBuilder<ContactDTO, ContactEntity> buildContactDTO = (entity) -> {
		ContactDTO dto = new ContactDTO();
		dto.setId(entity.getId());
		dto.setPhoneNumber(entity.getPhoneNumber());
		dto.setWorkNumber(entity.getWorkNumber());
		dto.setHomeNumber(entity.getHomeNumber());
		dto.setEmail(entity.getEmail());
		return dto;
	};
	
	public static IBuilder<EmployeeDTO, EmployeeEntity> buildEployeeDTO = (entity) -> {
		EmployeeDTO dto = new EmployeeDTO();
		dto.setId(entity.getId());
		dto.setAlternativeId(entity.getAlternativeId());
		dto.setUserInfo(buildUserInfoDTO.build(entity.getUserInfo()));
		dto.setAddress(buildAddressDTO.build(entity.getAddress()));
		dto.setContact(buildContactDTO.build(entity.getContact()));
		return dto;
	};
	
	
}
