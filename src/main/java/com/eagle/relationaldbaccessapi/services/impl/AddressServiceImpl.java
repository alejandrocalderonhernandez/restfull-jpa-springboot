package com.eagle.relationaldbaccessapi.services.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.logging.log4j.Logger;

import com.eagle.relationaldbaccessapi.models.dto.AddressDTO;
import com.eagle.relationaldbaccessapi.models.entity.AddressEntity;
import com.eagle.relationaldbaccessapi.repocitory.AddressRepository;
import com.eagle.relationaldbaccessapi.services.interfaces.IAddressService;
import com.eagle.relationaldbaccessapi.util.interfaces.IMapper;
import com.eagle.relationaldbaccessapi.util.interfaces.IUpdater;

@Service
public class AddressServiceImpl implements IAddressService {
	
    private static final Logger LOGGER = LogManager.getLogger(AddressServiceImpl.class);
    
	 private IMapper<AddressEntity, AddressDTO> mapToEntity  = addressDto -> {
			return new AddressEntity.Builder()
					.addId(addressDto.getId())
					.addColony(addressDto.getColony())
					.addTownHall(addressDto.getTownHall())
					.addEstate(addressDto.getEstate())
					.addStreet(addressDto.getStreet())
					.addInternalNumber(addressDto.getInternalNumber())
					.addExternalNumber(addressDto.getExternalNumber())
					.addLat(addressDto.getLat())
					.addLon(addressDto.getLon())
					.build();
	 	};
	 	
		private IMapper<AddressDTO, AddressEntity> mapToDTO  = addressEntity -> {
				return new AddressDTO.Builder()
						.addId(addressEntity.getId())
						.addColony(addressEntity.getColony())
						.addTownHall(addressEntity.getTownHall())
						.addEstate(addressEntity.getEstate())
						.addStreet(addressEntity.getStreet())
						.addInternalNumber(addressEntity.getInternalNumber())
						.addExternalNumber(addressEntity.getExternalNumber())
						.addLat(addressEntity.getLat())
						.addLon(addressEntity.getLon())
						.build();
		 	};
    
    private IUpdater<AddressDTO, AddressEntity> updateEntity = (newAddress, oldAddress) -> {
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
		AddressEntity addressToInsert = this.mapToEntity.mapObject(dto);
		LOGGER.info("Inserted {} ", dto);
		try {
			this.repocitory.save(addressToInsert);
		} catch (Exception e) {
			LOGGER.error("Error to insert Address: ", e);
		}
		return dto;
	}

	@Override
	@Transactional
	public AddressDTO update(AddressDTO dto, Long id) {
		if(this.repocitory.existsById(id)){
			try {
				AddressEntity addresToUpdate = this.repocitory.findById(id).get();
				updateEntity.update(dto, addresToUpdate);
				this.repocitory.save(addresToUpdate);
				LOGGER.info("Updated {} ", dto);
				return this.mapToDTO.mapObject(addresToUpdate);
			} catch (Exception e) {
				LOGGER.error("Error to update Address -> ", e);
				return dto;
			}
		} else {
			LOGGER.warn("Update Address not found id: " + id);
			throw new IllegalArgumentException("The Address id -> " + id + " dont exist");
		}
	}

	@Override
	@Transactional(readOnly = true)
	public AddressDTO findById(Long id) {
		if(this.repocitory.existsById(id)){
			return this.mapToDTO.mapObject(this.repocitory.findById(id).get());
		} else {
			LOGGER.warn("Select Address not found id: " + id);
			throw new IllegalArgumentException("The Address with id:  " + id + " dont exist");
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<AddressDTO> findAll() {
		List<AddressEntity>resultEntity = this.repocitory.findAll();
		if(!resultEntity.isEmpty()) {
			List<AddressDTO> resultDTO = new LinkedList<>();
			resultEntity.forEach(address ->  resultDTO.add(this.mapToDTO.mapObject(address)));
			return resultDTO;
		} else {
			LOGGER.warn("Find all no data: Address");;
			throw new NoSuchElementException("Database is empty");
		}
	}

	@Override
	@Transactional
	public boolean deleteById(Long id) {
		if(this.repocitory.existsById(id)) {
			this.repocitory.deleteById(id);
			LOGGER.info("Deleted Address with id: " + id);
			return true;
		} else {
			LOGGER.warn("Delete Address not found id: " + id);
			return false;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public boolean existById(Long id) {
		return this.repocitory.existsById(id);
	}
}
