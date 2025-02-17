package com.cdac.trustvault.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.cdac.trustvault.entity.UserEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

	Optional<UserEntity> findByEmailAndPassword(String em,String pwd);

	Optional<UserEntity> findByEmail(String email);
	

}
