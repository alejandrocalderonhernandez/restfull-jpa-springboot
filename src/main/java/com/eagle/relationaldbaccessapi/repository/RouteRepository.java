package com.eagle.relationaldbaccessapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eagle.relationaldbaccessapi.models.entity.RouteEntity;

public interface RouteRepository extends JpaRepository<RouteEntity, Long>{

}
