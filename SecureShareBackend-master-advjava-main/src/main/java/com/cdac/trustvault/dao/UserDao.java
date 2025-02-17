
package com.cdac.trustvault.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cdac.trustvault.entity.UserEntity;

public interface UserDao extends JpaRepository<UserEntity, Long> {

	Optional<UserEntity> findByEmailAndPassword(String em,String pwd);

	Optional<UserEntity> findByEmail(String email);

}