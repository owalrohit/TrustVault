package com.cdac.trustvault.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cdac.trustvault.entity.Document;

public interface DocumentRepository extends JpaRepository<Document, Long>{
	 List<Document> findByUploadedBy(String uploadedBy);
	 
	 List<Document> findByUploadedByAndUploaderEmail(String uploadedBy, String uploaderEmail);
}
