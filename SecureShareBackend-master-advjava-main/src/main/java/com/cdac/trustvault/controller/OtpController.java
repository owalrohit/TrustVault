package com.cdac.trustvault.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.trustvault.config.JwtTokenProvider;
import com.cdac.trustvault.dto.OtpApiResponse;
import com.cdac.trustvault.dto.OtpDto;
import com.cdac.trustvault.dto.OtpResDto;
import com.cdac.trustvault.entity.UserEntity;
import com.cdac.trustvault.service.UserService;
import com.cdac.trustvault.service.UserServiceImpl;
import com.cdac.trustvault.services.OtpService;

@RestController
@RequestMapping("/otp")
@CrossOrigin(origins = "http://localhost:5173")
public class OtpController {

	@Autowired
	private OtpService otpService;
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	private UserServiceImpl userService;

	@PostMapping(value = "/verifyotp", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> verifyOtp(@RequestBody OtpDto reqDto) {
		try {
			OtpResDto respDto = otpService.verifyOtp(reqDto);
			String res = respDto.getRole().toString();
			UserEntity user = userService.getUserByEmail(reqDto.getEmail());
			String token = jwtTokenProvider.createToken(user.getEmail(),user.getRole().toString(),user.getEmail());
			return ResponseEntity.ok(new OtpApiResponse(true, "OTP verified successfully", res,token,user.getName(),user.getEmail()));
		} catch (Exception ex) {
			return ResponseEntity.status(400).body(new OtpApiResponse(false, ex.getMessage(), null));
		}
	}

}
