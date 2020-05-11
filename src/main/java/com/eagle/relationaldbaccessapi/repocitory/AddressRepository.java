package com.eagle.relationaldbaccessapi.repocitory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eagle.relationaldbaccessapi.models.entity.AddressEntity;

public interface AddressRepository extends JpaRepository<AddressEntity, Long>{

}
