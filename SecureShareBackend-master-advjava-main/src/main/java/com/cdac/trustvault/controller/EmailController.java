package com.cdac.trustvault.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.trustvault.services.OTPEmailService;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class EmailController {

	@Autowired
	private OTPEmailService emailService;

	@PostMapping("/sendOtp/{email}")
	public String sendMail(@PathVariable("email") String email) {
		emailService.sendOTP(email);

		return "otp send successfully";
	}
	
	
	@PostMapping("/forgotpass/{email}")
	public String forgotPassMail(@PathVariable("email") String email) {
		emailService.sendOTP(email);
		return "otp send successfully";
	}

}