package com.cdac.trustvault.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cdac.trustvault.entity.UserEntity;

public interface UserRoleRepo  extends JpaRepository<UserEntity, Long> {
	
	Optional<UserEntity> findByEmail(String email);
}
