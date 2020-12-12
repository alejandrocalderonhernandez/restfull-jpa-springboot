package com.eagle.relationaldbaccessapi.util.strategies;

import java.time.LocalDateTime;
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
import lombok.NoArgsConstructor;

public class BuilderEntityWithRelationStrategies {

	private BuilderEntityWithRelationStrategies() {
	}

	public static final IBuilder<AddressEntity, AddressDTO> BUILD_ADDRESS_ENTITY_WITH_EMPLOYEE = (dto) -> {
		AddressEntity entity = new AddressEntity();
		entity.setStreet(dto.getStreet());
		entity.setColony(dto.getColony());
		entity.setTownHall(dto.getTownHall());
		entity.setEstate(dto.getEstate());
		entity.setInternalNumber(dto.getInternalNumber());
		entity.setExternalNumber(dto.getExternalNumber());
		entity.setLat(dto.getLat());
		entity.setLon(dto.getLon());
		if (dto.getEmployee() != null) {
			entity.setEmployee(BuilderSimpleEntityStrategies.BUILD_EMPLOYEE_ENTITY.build(dto.getEmployee()));
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
		entity.setCreateAt(LocalDateTime.now());
		entity.setAge(dto.getAge());
		entity.setStatus(true);
		if (dto.getEmployee() != null) {
			entity.setEmployee(BuilderSimpleEntityStrategies.BUILD_EMPLOYEE_ENTITY.build(dto.getEmployee()));
		}
		return entity;
	};

	public static final IBuilder<ContactEntity, ContactDTO> BUILD_CONTACT_ENTITY = (dto) -> {
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
		if (dto.getAddress() != null) {
			entity.setAddress(BuilderSimpleEntityStrategies.BUILD_ADDRESS_ENTITY.build(dto.getAddress()));
		}
		if (dto.getContact() != null) {
			entity.setContact(BuilderSimpleEntityStrategies.BUILD_CONTACT_ENTITY.build(dto.getContact()));
		}
		return entity;
	};

	public static final IBuilder<OriginEntity, OriginDTO> BUILDER_ORIGIN_ENTITY = (dto) -> {
		OriginEntity entity = new OriginEntity();
		entity.setAlternativeId(dto.getAlternativeId());
		entity.setName(dto.getName());
		entity.setType(dto.getType());
		entity.setAddress(BuilderSimpleEntityStrategies.BUILD_ADDRESS_ENTITY.build(dto.getAddress()));
		if (!dto.getRoutes().isEmpty()) {
			entity.setRoutes(dto.getRoutes().stream().map(BuilderSimpleEntityStrategies.BUILDER_ROUTE_ENTITY::build)
					.collect(Collectors.toSet()));
		}
		return entity;
	};

	public static final IBuilder<DestinationEntity, DestinationDTO> BUILDER_DESTINATION_ENTITY = (dto) -> {
		DestinationEntity entity = new DestinationEntity();
		entity.setAlternativeId(dto.getAlternativeId());
		entity.setFinalDestination(dto.getFinalDestination());
		entity.setName(dto.getName());
		entity.setType(dto.getType());
		entity.setAddress(BuilderSimpleEntityStrategies.BUILD_ADDRESS_ENTITY.build(dto.getAddress()));
		if (!dto.getRoutes().isEmpty()) {
			entity.setRoutes(dto.getRoutes().stream().map(BuilderSimpleEntityStrategies.BUILDER_ROUTE_ENTITY::build)
					.collect(Collectors.toSet()));
		}
		return entity;
	};

	public static final IBuilder<RouteEntity, RouteDTO> BUILDER_ROUTE_ENTITY = (dto) -> {
		RouteEntity entity = new RouteEntity();
		entity.setAlternativeId(dto.getAlternativeId());
		entity.setDirectTravel(dto.getDirectTravel());
		entity.setName(dto.getName());
		entity.setOrigin(BuilderSimpleEntityStrategies.BUILDER_ORIGIN_ENTITY.build(dto.getOrigin()));
		entity.setDestinations(dto.getDestinations().stream()
				.map(BuilderSimpleEntityStrategies.BUILDER_DESTINATION_ENTITY::build).collect(Collectors.toSet()));
		return entity;
	};

}
