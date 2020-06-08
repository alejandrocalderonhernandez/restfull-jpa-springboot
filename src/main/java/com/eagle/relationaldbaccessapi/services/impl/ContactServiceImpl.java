package com.eagle.relationaldbaccessapi.services.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eagle.relationaldbaccessapi.models.dto.ContactDTO;
import com.eagle.relationaldbaccessapi.models.entity.ContactEntity;
import com.eagle.relationaldbaccessapi.repository.ContactRepository;
import com.eagle.relationaldbaccessapi.services.interfaces.IContactService;
import com.eagle.relationaldbaccessapi.util.interfaces.functional.IUpdater;
import com.eagle.relationaldbaccessapi.util.util.StringUtil;

@Service
public class ContactServiceImpl implements IContactService{
	
	private static final String TYPE = "Contact";
    private static final Logger LOGGER = LogManager.getLogger(ContactServiceImpl.class);
    
	private ContactRepository repocitory;
	
	private IUpdater<ContactDTO, ContactEntity> updater = (newContact, oldContact) -> {
		oldContact.setPhoneNumber(newContact.getPhoneNumber());
		oldContact.setWorkNumber(newContact.getWorkNumber());
		oldContact.setHomeNumber(newContact.getHomeNumber());
		oldContact.setEmail(oldContact.getEmail());
	};
	
	@Autowired
	public ContactServiceImpl(ContactRepository repository) {
		this.repocitory = repository;
	}

	@Override
	@Transactional
	public ContactDTO insert(ContactDTO dto) {
		ContactEntity entity = new ContactEntity(dto);
		try {
			this.repocitory.save(entity);
			LOGGER.info("Inserted {} ", dto);
		} catch (Exception e) {
			LOGGER.error("Error to insert Contact: ", e);
		}
		return dto;
	}

	@Override
	@Transactional
	public ContactDTO update(ContactDTO dto, Long id) {
		if(this.repocitory.existsById(id)){
			try {
				ContactEntity contactToUpdate = new ContactEntity(dto);
				this.updater.update(dto, contactToUpdate);
				this.repocitory.save(contactToUpdate);
				LOGGER.info("Updated {} ", dto);
				return new ContactDTO(contactToUpdate);
			} catch (Exception e) {
				LOGGER.error("Error to update Contact: ", e);
				return dto;
			}
		} else {
			 LOGGER.warn("Update Contact not found id: " + id);
			throw new IllegalArgumentException(StringUtil.badIdMessage(TYPE, id));
		}
	}

	@Override
	@Transactional(readOnly = true)
	public ContactDTO findById(Long id) {
		if(this.repocitory.existsById(id)) {
			return new ContactDTO(this.repocitory.findById(id).get());
		} else {
			 LOGGER.warn("Select Contact not found id: " + id);
			throw new IllegalArgumentException(StringUtil.badIdMessage(TYPE, id));
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<ContactDTO> findAll() {
		return null;
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
	}

}
