package com.cdac.trustvault.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cdac.trustvault.entity.DocumentSharing;

@Repository
public interface DocumentSharingRepository extends JpaRepository<DocumentSharing, Long> {
	 List<DocumentSharing> findBySharedWith(String sharedWith);
}