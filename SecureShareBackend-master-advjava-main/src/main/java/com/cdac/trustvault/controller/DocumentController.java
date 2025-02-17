package com.cdac.trustvault.controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.cdac.trustvault.entity.Document;
import com.cdac.trustvault.entity.DocumentSharing;
import com.cdac.trustvault.repository.DocumentRepository;
import com.cdac.trustvault.repository.DocumentSharingRepository;
import com.cdac.trustvault.service.DocumentService;
import com.cdac.trustvault.service.EmailService;
import com.cdac.trustvault.util.FileEncryptionUtil;



@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/document")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @Autowired
    private EmailService emailService;
    
    @Autowired
    DocumentRepository documentRepository;

    
    @Autowired	
    DocumentSharingRepository documentSharingRepository;
    
    @GetMapping
    public List<Document> getAllDocuments() {
        return documentService.getAllDocuments();
    }
    
    @PostMapping("/upload")
    public ResponseEntity<?> uploadDocument(@RequestParam("file") MultipartFile file, 
                                            @RequestParam("uploadedBy") String uploadedBy,@RequestParam("uploaderEmail") String uploaderEmail) {
        try {
            System.out.println("Inside file Upload");
            Document document = documentService.uploadDocument(file, uploadedBy,uploaderEmail);
            return ResponseEntity.ok(document);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadDocument(@PathVariable Long id) {
        try {
            Document document = documentRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Document not found"));

            // Get the decrypted content
            SecretKey key = FileEncryptionUtil.decodeKey(document.getEncryptionKey());
            byte[] decryptedContent = FileEncryptionUtil.decrypt(document.getEncryptedContent(), key);

            // Set headers to allow inline preview
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType(document.getType()));  // Set correct MIME type
            headers.setContentDisposition(ContentDisposition.inline().filename(document.getName()).build());

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(decryptedContent);
        } catch (Exception e) {
            return ResponseEntity.status(404).body(null);
        }
    }

    @GetMapping("/getAllDocument")
    public ResponseEntity<List<Document>> getDocumentsByUploader(
            @RequestParam String uploadedBy,
            @RequestParam String uploaderEmail) {
        try {
            List<Document> documents = documentService.getDocumentsByUploader(uploadedBy, uploaderEmail);
            return ResponseEntity.ok(documents);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @DeleteMapping("deleteDocument/{id}")
    public ResponseEntity<String> deleteDocument(@PathVariable Long id) {
        try {
            boolean isDeleted = documentService.deleteDocument(id);
            if (isDeleted) {
                return ResponseEntity.ok("Document deleted successfully!");
            } else {
                return ResponseEntity.status(404).body("Document not found.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to delete document: " + e.getMessage());
        }
    }

   
    @PostMapping("/share")
    public ResponseEntity<String> shareDocument(@RequestBody DocumentSharing request) {
    	System.out.println("Received document sharing request: " + request);
        try {
            // Fetch the document by documentId from the request
            Document document = documentRepository.findById(request.getDocumentId())
                    .orElseThrow(() -> new RuntimeException("Document not found"));

            // Ensure the document name is not null
            if (document.getName() == null) {
                return ResponseEntity.status(404).body("Document name is not available.");
            }

            // Create the sharing record
            DocumentSharing documentSharing = new DocumentSharing();
            documentSharing.setDocumentId(request.getDocumentId()); // Use documentId from request
            documentSharing.setSharedBy(request.getSharedBy()); // Who is sharing it
            documentSharing.setSharedWith(request.getSharedWith()); 
            LocalDateTime dateTime = LocalDateTime.now();
            documentSharing.setSharedAt(dateTime); // Set current time for sharing
            documentSharing.setDocumentName(request.getDocumentName());
            // Save the sharing record
            documentSharingRepository.save(documentSharing);

            // Send the email with the download link (call the service method after saving the document sharing)
            documentService.shareDocument(request, dateTime);

            return ResponseEntity.ok("Document shared successfully!");

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to share document: " + e.getMessage());
        }
    }

    
    @GetMapping("/test1")
    public void abc () {
    	
    	System.out.println("inside");
    	 DocumentSharing documentSharing = new DocumentSharing();
	        documentSharing.setDocumentId(15L); // Set the document ID directly
	        documentSharing.setSharedBy("Ashwini Patil"); // Who is sharing it
	        documentSharing.setSharedWith("paragparate279@gmail.com"); // Who it is being shared with
	        String dateTimeString = "2024-12-08T17:49:30.123"; 
	        LocalDateTime sharedAt = LocalDateTime.parse(dateTimeString); 
	       
	        
	        
	        documentSharing.setSharedAt(sharedAt); // When it was shared

	        // Save the sharing record
	        documentSharingRepository.save(documentSharing);
    }
    
    
}
