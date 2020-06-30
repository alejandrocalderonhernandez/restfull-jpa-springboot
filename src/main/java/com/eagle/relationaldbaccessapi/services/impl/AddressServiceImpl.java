package com.eagle.relationaldbaccessapi.services.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.logging.log4j.Logger;

import com.eagle.relationaldbaccessapi.models.dto.AddressDTO;
import com.eagle.relationaldbaccessapi.models.entity.AddressEntity;
import com.eagle.relationaldbaccessapi.repository.AddressRepository;
import com.eagle.relationaldbaccessapi.services.interfaces.IAddressService;
import com.eagle.relationaldbaccessapi.util.interfaces.functional.IUpdater;
import com.eagle.relationaldbaccessapi.util.strategies.BuilderDTOSWithRelationStrategies;
import com.eagle.relationaldbaccessapi.util.strategies.BuilderEntityWithRelationStrategies;
import com.eagle.relationaldbaccessapi.util.strategies.BuilderSimpleDTOStrategies;
import com.eagle.relationaldbaccessapi.util.strategies.BuilderSimpleEntityStrategies;
import com.eagle.relationaldbaccessapi.util.util.StringUtil;

@Service
public class AddressServiceImpl implements IAddressService {
	
    private static final Logger LOGGER = LogManager.getLogger(AddressServiceImpl.class);
    
    private IUpdater<AddressDTO, AddressEntity> updater = (newAddress, oldAddress) -> {
	    	 oldAddress.setStreet(newAddress.getStreet());
	    	 oldAddress.setColony(newAddress.getColony());
	    	 oldAddress.setTownHall(newAddress.getTownHall());
	    	 oldAddress.setEstate(newAddress.getStreet());
	    	 oldAddress.setExternalNumber(newAddress.getExternalNumber());
	    	 oldAddress.setInternalNumber(newAddress.getInternalNumber());
	       	 oldAddress.setLat(newAddress.getLat());
	    	 oldAddress.setLon(newAddress.getLon());
    };
	 
	private AddressRepository repocitory;
	
	@Autowired
	public AddressServiceImpl(AddressRepository repository) {
		this.repocitory = repository;
	}

	@Override
	@Transactional
	public AddressDTO insert(AddressDTO dto) {
		AddressDTO response = null;
		try {
			AddressEntity addressToInsert = this.selectBuilderEntity(dto);
			response = this.selectBuilderDTO(this.repocitory.save(addressToInsert));
			LOGGER.info("Inserted {} ", dto);
		} catch (Exception e) {
			LOGGER.error("Error to insert Address: ", e);
		}
		return response;
	}

	@Override
	@Transactional
	public AddressDTO update(AddressDTO dto, Long id) {
		Optional<AddressEntity> responce = (this.repocitory.findById(id));
		if(responce.isPresent()){
			try {
				AddressEntity addresToUpdate = responce.get();
				updater.update(dto, addresToUpdate);
				this.repocitory.save(addresToUpdate);
				LOGGER.info("Updated {} ", dto);
				return this.selectBuilderDTO(addresToUpdate);
			} catch (Exception e) {
				LOGGER.error("Error to update Address: ", e);
				return dto;
			}
		} else {
		   LOGGER.warn("Update Address not found id: " + id);
			throw new IllegalArgumentException(StringUtil.badIdMessage(TYPE, id));
		}
	}

	@Override
	@Transactional(readOnly = true)
	public AddressDTO findById(Long id) {
		Optional<AddressEntity> response = (this.repocitory.findById(id));
		if(response.isPresent()){
			return this.selectBuilderDTO(response.get());
		} else {
			LOGGER.warn("Select Address not found id: " + id);
			throw new IllegalArgumentException(StringUtil.badIdMessage(TYPE, id));
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<AddressDTO> findAll() {
		List<AddressEntity>response = this.repocitory.findAll();
		if(!response.isEmpty()) {
			return response.stream().map(this::selectBuilderDTO)
					.collect(Collectors.toList());
		} else {
			LOGGER.warn("Find all, no data: Address");
			throw new NoSuchElementException("Database is empty");
		}
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		if(this.repocitory.existsById(id)) {
			this.repocitory.deleteById(id);
			LOGGER.info("Deleted Address with id: " + id);
		} else {
			LOGGER.warn("Delete Address not found id: " + id);
			throw new IllegalArgumentException(StringUtil.badIdMessage(TYPE, id));
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Page<AddressDTO> getPageByRange(int page, int limit) {
		Pageable range = PageRequest.of(page, limit);
		Page<AddressEntity>responsePage = this.repocitory.findAll(range);
		if(!responsePage.isEmpty()) {
			return responsePage.map(this::selectBuilderDTO);
		} else {
			LOGGER.warn("Find all no data: Address");
			throw new NoSuchElementException("Database is empty");
		}
	}

	@Override
	public Page<AddressDTO> getPageByRangeSortByName(int page, int limit) {
		Pageable range = PageRequest.of(page, limit, Sort.by(DEFAULT_NAME_TO_SORT));
		Page<AddressEntity>responsePage = this.repocitory.findAll(range);
		if(!responsePage.isEmpty()) {
			return responsePage.map(this::selectBuilderDTO);
		} else {
			LOGGER.warn("Find all no data: Address");
			throw new NoSuchElementException("Database is empty");
		}
	}
	
	private AddressEntity selectBuilderEntity(final AddressDTO dto) {
		AddressEntity result = null;
		if (dto.getEmployee() != null) {
			result = BuilderEntityWithRelationStrategies.BUILD_ADDRESS_ENTITY_WITH_EMPLOYEE.build(dto);
		    result.getEmployee().setAddress(result);
		} else {
			result = BuilderSimpleEntityStrategies.BUILD_ADDRESS_ENTITY.build(dto);
		}
		return result;
	}
	
	private AddressDTO selectBuilderDTO(final AddressEntity entity) {
		if (entity.getEmployee() != null) {
			return BuilderDTOSWithRelationStrategies.BUILD_ADDRESS_DTO_WITH_EMPLOYEE.build(entity);
		} else {
			return BuilderSimpleDTOStrategies.BUILD_ADDRESS_DTO.build(entity);
		}
	}
}
