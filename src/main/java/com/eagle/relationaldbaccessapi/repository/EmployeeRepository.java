package com.eagle.relationaldbaccessapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eagle.relationaldbaccessapi.models.entity.EmployeeEntity;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

	public Optional<EmployeeEntity> findByAlternativeId(String alternativeId);
	
}
