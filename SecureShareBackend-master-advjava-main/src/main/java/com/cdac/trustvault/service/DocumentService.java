package com.cdac.trustvault.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cdac.trustvault.entity.Document;
import com.cdac.trustvault.entity.DocumentSharing;
import com.cdac.trustvault.repository.DocumentRepository;
import com.cdac.trustvault.repository.DocumentSharingRepository;
import com.cdac.trustvault.util.FileEncryptionUtil;

import jakarta.transaction.Transactional;

@Service
public class DocumentService {
	
	 	@Autowired
	    private DocumentRepository documentRepository;
	 	
	 	@Autowired
	 	private DocumentSharingRepository documentSharingRepository;

	    @Autowired
	    private EmailService emailService;
	    
	    public List<Document> getAllDocuments() {
	        return documentRepository.findAll();
	    }

	    public Document uploadDocument(MultipartFile file,String uploadedBy,String uploaderEmail) throws Exception {
	        SecretKey key = FileEncryptionUtil.generateKey();
	        byte[] encryptedContent = FileEncryptionUtil.encrypt(file.getBytes(), key);

	        Document document = new Document();
	        document.setName(file.getOriginalFilename());
	        document.setType(file.getContentType());
	        document.setSize(file.getSize());
	        document.setUploadedAt(LocalDateTime.now());
	        document.setEncryptedContent(encryptedContent);
	        document.setEncryptionKey(FileEncryptionUtil.encodeKey(key));
	        document.setUploadedBy(uploadedBy);
	        document.setUploaderEmail(uploaderEmail);

	        return documentRepository.save(document);
	    }

	    public byte[] downloadDocument(Long id) throws Exception {
	        Document document = documentRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Document not found"));
	        SecretKey key = FileEncryptionUtil.decodeKey(document.getEncryptionKey());
	        return FileEncryptionUtil.decrypt(document.getEncryptedContent(), key);
	    }

	    public List<Document> getDocumentsByUploader(String uploadedBy, String uploaderEmail) {
	        // Fetch uploaded documents by the uploader
	        List<Document> uploadedDocuments = documentRepository.findByUploadedByAndUploaderEmail(uploadedBy, uploaderEmail);
	        
	        // Fetch shared documents info for the uploader
	        List<DocumentSharing> sharedDocumentsInfo = documentSharingRepository.findBySharedWith(uploaderEmail);
	        List<Long> sharedDocumentIds = sharedDocumentsInfo.stream()
	                .map(DocumentSharing::getDocumentId)
	                .collect(Collectors.toList());
	        
	        // Fetch shared documents using the shared document IDs
	        List<Document> sharedDocuments = documentRepository.findAllById(sharedDocumentIds);
	        
	        // Attach sharing details to the document
	        for (Document document : sharedDocuments) {
	            DocumentSharing sharingInfo = sharedDocumentsInfo.stream()
	                .filter(docSharing -> docSharing.getDocumentId().equals(document.getId()))
	                .findFirst()
	                .orElse(null);
	            
	            if (sharingInfo != null) {
	                document.setShared(true);  // Mark the document as shared
	                // Optionally, you can attach the sharing information to the document or create a new field.
	                document.setSharedBy(sharingInfo.getSharedBy());
	                document.setSharedWith(sharingInfo.getSharedWith());
	                document.setSharedAt(sharingInfo.getSharedAt());
	            }
	        }
	        
	        // Combine uploaded and shared documents
	        uploadedDocuments.addAll(sharedDocuments);
	        
	        // Return documents directly
	        return uploadedDocuments;
	    }
	    
	    @Transactional
	    public boolean deleteDocument(Long id) {
	        try {
	            Document document = documentRepository.findById(id).orElse(null);
	            if (document != null) {
	                documentRepository.delete(document); // Delete the document from the repository
	                return true;
	            }
	            return false;
	        } catch (Exception e) {
	            throw new RuntimeException("Error occurred while deleting document", e);
	        }
	    }
	    
	    @Transactional
	    public boolean shareDocument(DocumentSharing request,LocalDateTime sharedAt) {
	    	   String loginLink = "http://localhost:5173/login";  
	    	    String subject = "Document Shared with You: " + request.getDocumentName() + " by "+request.getSharedBy();  // Subject with document name and shared by info
	    	    String body = String.format(
	    	    	    "Hello,<br><span style='margin-left:40px;'><b>%s</b> has shared the document \"<b>%s</b>\" with you on TrustVault.</span> " +
	    	    	    "Please register or log in to your TrustVault account to view the document.<br>" +
	    	    	    "<span style='margin-left:40px;'><b>Login here:</b> <a href='%s'>%s</a></span>",
	    	    	    request.getSharedBy(), request.getDocumentName(), loginLink, loginLink
	    	    	);
	    	    //  http://localhost:8080/documents/download/15
	    	    boolean emailSent = emailService.sendEmail(request.getSharedWith(), subject, body);

	    	    if (!emailSent) {
	    	        System.err.println("Failed to send email to " + request.getSharedWith());
	    	    } else {
	    	        System.out.println("Email sent successfully to " + request.getSharedWith());
	    	    }
	    	    return emailSent;
	    }
}
