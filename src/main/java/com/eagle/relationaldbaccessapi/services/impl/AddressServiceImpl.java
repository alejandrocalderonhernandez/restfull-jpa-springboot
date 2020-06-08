package com.eagle.relationaldbaccessapi.services.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.logging.log4j.Logger;

import com.eagle.relationaldbaccessapi.models.dto.AddressDTO;
import com.eagle.relationaldbaccessapi.models.entity.AddressEntity;
import com.eagle.relationaldbaccessapi.repository.AddressRepository;
import com.eagle.relationaldbaccessapi.services.interfaces.IAddressService;
import com.eagle.relationaldbaccessapi.util.interfaces.functional.IUpdater;
import com.eagle.relationaldbaccessapi.util.util.StringUtil;

@Service
public class AddressServiceImpl implements IAddressService {
	
	private static final String TYPE = "Address";
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
		AddressEntity responce = null;
		try {
			AddressEntity addressToInsert = new AddressEntity(dto);
			responce = this.repocitory.save(addressToInsert);
			LOGGER.info("Inserted {} ", dto);
		} catch (Exception e) {
			LOGGER.error("Error to insert Address: ", e);
		}
		return new AddressDTO(responce);
	}

	@Override
	@Transactional
	public AddressDTO update(AddressDTO dto, Long id) {
		if(this.repocitory.existsById(id)){
			try {
				AddressEntity addresToUpdate = this.repocitory.findById(id).get();
				updater.update(dto, addresToUpdate);
				this.repocitory.save(addresToUpdate);
				LOGGER.info("Updated {} ", dto);
				return new AddressDTO(addresToUpdate);
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
		if(this.repocitory.existsById(id)){
			return new AddressDTO(this.repocitory.findById(id).get());
		} else {
			LOGGER.warn("Select Address not found id: " + id);
			throw new IllegalArgumentException(StringUtil.badIdMessage(TYPE, id));
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<AddressDTO> findAll() {
		List<AddressEntity>resultEntity = this.repocitory.findAll();
		if(!resultEntity.isEmpty()) {
			return resultEntity.stream().map(AddressDTO::new)
					.collect(Collectors.toList());
		} else {
			LOGGER.warn("Find all no data: Address");
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
		Page<AddressEntity>resultEntity = this.repocitory.findAll(range);
		if(!resultEntity.isEmpty()) {
			return resultEntity.map(AddressDTO::new);
		} else {
			LOGGER.warn("Find all no data: Address");
			throw new NoSuchElementException("Database is empty");
		}
	}
}
