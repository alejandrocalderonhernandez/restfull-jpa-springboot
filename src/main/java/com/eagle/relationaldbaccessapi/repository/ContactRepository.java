package com.eagle.relationaldbaccessapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eagle.relationaldbaccessapi.models.entity.ContactEntity;

public interface ContactRepository extends JpaRepository<ContactEntity, Long>{

}
