package com.cdac.trustvault.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cdac.trustvault.entity.Otp;

@Repository
public interface OtpRepository extends JpaRepository<Otp, Long>{
	
	Optional<Otp> findByEmailAndOtp(String em,String otp);
	
	@Query("SELECT o FROM Otp o WHERE o.email = :email ORDER BY o.expirationTime DESC limit 1") 
	Optional<Otp> findLatestByEmail(@Param("email") String email);

	
}
