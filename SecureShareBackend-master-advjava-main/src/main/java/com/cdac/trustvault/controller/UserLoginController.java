package com.cdac.trustvault.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.trustvault.dto.ApiResponse;
import com.cdac.trustvault.dto.AuthRequest;
import com.cdac.trustvault.dto.PasswordResetRequest;
import com.cdac.trustvault.dto.UserRespDTO;
import com.cdac.trustvault.entity.UserEntity;
import com.cdac.trustvault.services.OTPEmailService;
import com.cdac.trustvault.services.OtpService;
import com.cdac.trustvault.services.OTPUserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:5173")
public class UserLoginController {

	@Autowired
	private OTPUserService userService;

	@Autowired
	private OTPEmailService emailService;

	@Autowired
	private OtpService otpService;

	public UserLoginController() {
		System.out.println("inside ctor : " + getClass());
	}

	@PostMapping()
	public ResponseEntity<?> addUser(@RequestBody UserEntity newUser) {
		return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(userService.addNewUser(newUser)));
	}

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@RequestBody @Valid AuthRequest dto) {
		UserRespDTO respDTO = userService.authenticate(dto);
		emailService.sendOTP(dto.getEmail());
		return ResponseEntity.ok("OTP sent to your email.");
	}

	@PostMapping("/reset-password")
	public ResponseEntity<?> resetPassword(@RequestBody PasswordResetRequest passwordResetRequest) {
		try {
			boolean otpVerified = otpService.verifyResetOtp(passwordResetRequest);
			if (otpVerified) {
				userService.updatePassword(passwordResetRequest);
				return ResponseEntity.ok("Password updated successfully.");
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid OTP.");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error resetting password: " + e.getMessage());
		}
	}

}
