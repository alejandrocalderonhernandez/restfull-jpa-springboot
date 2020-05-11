package com.eagle.relationaldbaccessapi.services.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.Logger;

import com.eagle.relationaldbaccessapi.models.dto.AddressDTO;
import com.eagle.relationaldbaccessapi.models.entity.AddressEntity;
import com.eagle.relationaldbaccessapi.repocitory.AddressRepository;
import com.eagle.relationaldbaccessapi.services.interfaces.IAddressService;
import com.eagle.relationaldbaccessapi.util.interfaces.IMapper;
import com.eagle.relationaldbaccessapi.util.interfaces.IUpdater;

@Service
public class AddressServiceImpl implements IAddressService{
	
    private static final Logger LOGGER = LogManager.getLogger(AddressServiceImpl.class);

	
	 IMapper<AddressEntity, AddressDTO> mapToEntity  = (addressDto) -> {
				return new AddressEntity(
						addressDto.getId(), addressDto.getStreet(),
						addressDto.getColony(), addressDto.getTownHall(),
						addressDto.getEstate(), addressDto.getExternalNumber(), 
						addressDto.getInternalNumber(), addressDto.getLat(), 
						addressDto.getLon());
	};
	
	 IMapper<AddressDTO, AddressEntity> mapToDTO  = (addressEntity) -> {
			  return new AddressDTO(
					  addressEntity.getId(), addressEntity.getStreet(),
					  addressEntity.getColony(), addressEntity.getTownHall(),
					  addressEntity.getEstate(), addressEntity.getExternalNumber(), 
					  addressEntity.getInternalNumber(), addressEntity.getLat(), 
					  addressEntity.getLon());
    };
    
    IUpdater<AddressDTO, AddressEntity> updateEntity = (newAddress, oldAddress) -> {
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
	public AddressDTO insert(AddressDTO dto) {
		AddressEntity addressToInsert = this.mapToEntity.mapObjectToEntity(dto);
		try {
			this.repocitory.save(addressToInsert);
		} catch (Exception e) {
			LOGGER.error("Error to insert address", e);
		}
		return dto;
	}

	@Override
	public AddressDTO update(AddressDTO dto, Long id) {
		if(this.repocitory.existsById(id)){
			AddressEntity addresToUpdate = this.repocitory.findById(id).get();
			updateEntity.update(dto, addresToUpdate);
			this.repocitory.save(addresToUpdate);
			return dto;
		} else {
			LOGGER.error("[update] Error to update address, id -> " + id+ " don´t exist on database");
			throw new IllegalArgumentException("The addres id -> " + id + " don´t exist");
		}
	}

	@Override
	public AddressDTO findById(Long id) {
		if(this.repocitory.existsById(id)){
			return this.mapToDTO.mapObjectToEntity(this.repocitory.findById(id).get());
		} else {
			LOGGER.error("[findById]: Error to get address, id -> " + id+ " don´t exist in database");
			throw new IllegalArgumentException("the addres id -> " + id + " don´t exist");
		}
	}

	@Override
	public List<AddressDTO> findAll() {
		List<AddressEntity>resultEntity = this.repocitory.findAll();
		if(!resultEntity.isEmpty()) {
			List<AddressDTO> resultDTO = new LinkedList<>();
			resultEntity.forEach(address ->  resultDTO.add(this.mapToDTO.mapObjectToEntity(address)));
			return resultDTO;
		} else {
			LOGGER.error("[findAll]: Not data in database");
			throw new NoSuchElementException("Not data in database");
		}
	}

	@Override
	public boolean deleteById(Long id) {
		if(this.repocitory.existsById(id)) {
			this.repocitory.deleteById(id);
			return true;
		} else {
			return false;
		}
	}
}
