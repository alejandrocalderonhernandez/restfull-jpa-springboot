package com.eagle.relationaldbaccessapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eagle.relationaldbaccessapi.models.entity.EmployeeEntity;

public interface EmployeeRepocitory extends JpaRepository<EmployeeEntity, Long> {

}
