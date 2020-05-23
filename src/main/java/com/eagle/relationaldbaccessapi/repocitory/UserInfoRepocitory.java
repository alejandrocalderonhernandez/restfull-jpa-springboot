package com.eagle.relationaldbaccessapi.repocitory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eagle.relationaldbaccessapi.models.entity.UserInfoEntity;

public interface UserInfoRepocitory extends JpaRepository<UserInfoEntity, Long>{

}
