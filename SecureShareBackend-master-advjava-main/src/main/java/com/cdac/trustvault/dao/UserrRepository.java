package com.cdac.trustvault.dao;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cdac.trustvault.entity.UserEntity;

public interface UserrRepository extends JpaRepository<UserEntity, Long> {
	Optional<UserEntity> findByEmail(String email);
}
