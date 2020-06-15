package com.eagle.relationaldbaccessapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eagle.relationaldbaccessapi.models.entity.OriginEntity;

public interface OriginRepository extends JpaRepository<OriginEntity, Long>{

}
