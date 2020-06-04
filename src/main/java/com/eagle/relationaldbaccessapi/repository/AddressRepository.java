package com.eagle.relationaldbaccessapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eagle.relationaldbaccessapi.models.entity.AddressEntity;

public interface AddressRepository extends JpaRepository<AddressEntity, Long>{

}
