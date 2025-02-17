package com.cdac.trustvault.repository;
//package com.trustvault.repository;

import com.cdac.trustvault.entity.ContactMessage;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactMessageRepository extends JpaRepository<ContactMessage, Long> {

	 @Query("SELECT u.email FROM UserEntity u WHERE u.role = 'ROLE_ADMIN'")
	  List<String> findAdminEmails();
	 
}
