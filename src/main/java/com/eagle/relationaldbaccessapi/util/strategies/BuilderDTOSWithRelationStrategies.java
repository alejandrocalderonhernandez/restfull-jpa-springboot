package com.eagle.relationaldbaccessapi.util.strategies;

import java.util.stream.Collectors;

import com.eagle.relationaldbaccessapi.models.dto.AddressDTO;
import com.eagle.relationaldbaccessapi.models.dto.ContactDTO;
import com.eagle.relationaldbaccessapi.models.dto.DestinationDTO;
import com.eagle.relationaldbaccessapi.models.dto.EmployeeDTO;
import com.eagle.relationaldbaccessapi.models.dto.OriginDTO;
import com.eagle.relationaldbaccessapi.models.dto.RouteDTO;
import com.eagle.relationaldbaccessapi.models.dto.UserInfoDTO;
import com.eagle.relationaldbaccessapi.models.entity.AddressEntity;
import com.eagle.relationaldbaccessapi.models.entity.ContactEntity;
import com.eagle.relationaldbaccessapi.models.entity.DestinationEntity;
import com.eagle.relationaldbaccessapi.models.entity.EmployeeEntity;
import com.eagle.relationaldbaccessapi.models.entity.OriginEntity;
import com.eagle.relationaldbaccessapi.models.entity.RouteEntity;
import com.eagle.relationaldbaccessapi.models.entity.UserInfoEntity;
import com.eagle.relationaldbaccessapi.util.interfaces.functional.IBuilder;

public class BuilderDTOSWithRelationStrategies {

	private BuilderDTOSWithRelationStrategies() {
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
		if (entity.getEmployee() != null) {
			dto.setEmployee(BuilderSimpleDTOStrategies.BUILD_EMPLOYEE_DTO.build(entity.getEmployee()));
		}
		return dto;
	};

	public static final IBuilder<AddressDTO, AddressEntity> BUILD_ADDRESS_DTO_WITH_EMPLOYEE = (entity) -> {
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
		if (entity.getEmployee() != null) {
			dto.setEmployee(BuilderSimpleDTOStrategies.BUILD_EMPLOYEE_DTO.build(entity.getEmployee()));
		}
		return dto;
	};

	public static final IBuilder<ContactDTO, ContactEntity> BUILD_CONTACT_DTO = (entity) -> {
		ContactDTO dto = new ContactDTO();
		dto.setId(entity.getId());
		dto.setPhoneNumber(entity.getPhoneNumber());
		dto.setWorkNumber(entity.getWorkNumber());
		dto.setHomeNumber(entity.getHomeNumber());
		dto.setEmail(entity.getEmail());
		if (entity.getEmployee() != null) {
			dto.setEmployee(BuilderSimpleDTOStrategies.BUILD_EMPLOYEE_DTO.build(entity.getEmployee()));
		}
		return dto;
	};

	public static final IBuilder<EmployeeDTO, EmployeeEntity> BUILD_EMPLOYEE_DTO = (entity) -> {
		EmployeeDTO dto = new EmployeeDTO();
		dto.setId(entity.getId());
		dto.setAlternativeId(entity.getAlternativeId());
		dto.setUserInfo(BuilderSimpleDTOStrategies.BUILD_USER_INFO_DTO.build(entity.getUserInfo()));
		if (entity.getAddress() != null) {
			dto.setAddress(BuilderSimpleDTOStrategies.BUILD_ADDRESS_DTO.build(entity.getAddress()));
		}
		if (entity.getContact() != null) {
			dto.setContact(BuilderSimpleDTOStrategies.BUILD_CONTACT_DTO.build(entity.getContact()));
		}
		return dto;
	};

	public static final IBuilder<OriginDTO, OriginEntity> BUILDER_ORIGIN_DTO = (entity) -> {
		OriginDTO dto = new OriginDTO();
		dto.setAlternativeId(entity.getAlternativeId());
		dto.setName(entity.getName());
		dto.setType(entity.getType());
		dto.setAddress(BuilderSimpleDTOStrategies.BUILD_ADDRESS_DTO.build(entity.getAddress()));
		if (!dto.getRoutes().isEmpty()) {
			dto.setRoutes(entity.getRoutes().stream().map(BuilderSimpleDTOStrategies.BUILDER_ROUTE_DTO::build)
					.collect(Collectors.toSet()));
		}
		return dto;
	};

	public static final IBuilder<DestinationDTO, DestinationEntity> BUILDER_DESTINATION_ENTITY = (entity) -> {
		DestinationDTO dto = new DestinationDTO();
		dto.setAlternativeId(entity.getAlternativeId());
		dto.setFinalDestination(entity.isFinalDestination());
		dto.setName(entity.getName());
		dto.setType(entity.getType());
		dto.setAddress(BuilderSimpleDTOStrategies.BUILD_ADDRESS_DTO.build(entity.getAddress()));
		if (!entity.getRoutes().isEmpty()) {
			dto.setRoutes(entity.getRoutes().stream().map(BuilderSimpleDTOStrategies.BUILDER_ROUTE_DTO::build)
					.collect(Collectors.toSet()));
		}
		return dto;
	};

	public static final IBuilder<RouteDTO, RouteEntity> BUILDER_ROUTE_DTO = (entity) -> {
		RouteDTO dto = new RouteDTO();
		dto.setAlternativeId(entity.getAlternativeId());
		dto.setDirectTravel(entity.isDirectTravel());
		dto.setName(entity.getName());
		dto.setOrigin(entity.getOrigin());
		dto.setDestinations(entity.getDestinations().stream()
				.map(BuilderSimpleDTOStrategies.BUILDER_DESTINATION_DTO::build).collect(Collectors.toSet()));
		return dto;
	};

}
