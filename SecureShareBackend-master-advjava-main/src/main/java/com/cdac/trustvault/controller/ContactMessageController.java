package com.cdac.trustvault.controller;
//package com.trustvault.controller;

import com.cdac.trustvault.dto.ContactRequest;
import com.cdac.trustvault.entity.ContactMessage;
import com.cdac.trustvault.repository.ContactMessageRepository;
import com.cdac.trustvault.service.EmailService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contactus")
@CrossOrigin(origins = "http://localhost:5173") // Adjust for frontend
public class ContactMessageController {
	
    private final ContactMessageRepository repository;
    
    @Autowired
    private EmailService emailService;
    
    public ContactMessageController(ContactMessageRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/submit")
    public ResponseEntity<String> submitContactForm(@Valid @RequestBody ContactMessage message) {
        repository.save(message); // Save the message first
        
        // Convert ContactMessage to ContactRequest (if needed)
        ContactRequest contactRequest = new ContactRequest();
        contactRequest.setMessage(message.getMessage());
        
        // Call sendContactQuery
        sendContactQuery(contactRequest);
        
        return ResponseEntity.ok("Message submitted successfully.");
    }

    @GetMapping("/messages")
    public List<ContactMessage> getAllMessages() {
        return repository.findAll();
    }
    
    @PostMapping("/send")
    public String sendContactQuery(@RequestBody ContactRequest contactRequest) {
        // Fetch all admin emails
        List<String> adminEmails = repository.findAdminEmails();

        // Send email notification to admins
        emailService.sendContactUsNotification(adminEmails, contactRequest.getMessage());

        return "Admins Notified Successfully";
    }
    
}
