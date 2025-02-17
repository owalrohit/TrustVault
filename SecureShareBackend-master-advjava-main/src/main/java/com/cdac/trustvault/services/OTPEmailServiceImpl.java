package com.cdac.trustvault.services;


import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.cdac.trustvault.dto.*;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class OTPEmailServiceImpl implements OTPEmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;
    
    @Autowired
    private OtpService otpService;

    
	@Override
	public void sendOTP(String email) {
		String otp = generateOtp();
		
		try {
			sendOtpToMail(email,otp);
			storeOtpToDb(email,otp);
		}catch(MessagingException e) {
			throw new RuntimeException("enable to send otp!!");
		}
		
	}
	
	private String generateOtp() {
		System.out.println("generateotp");
		SecureRandom random = new SecureRandom();
		int otp = 100000 + random.nextInt(900000);
		return String.valueOf(otp);
	}
	
	private void sendOtpToMail(String email ,String otp) throws MessagingException{
	
		
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
		mimeMessage.setFrom(sender);
		mimeMessageHelper.setTo(email);
		mimeMessageHelper.setSubject("TrustVault Code");
		mimeMessageHelper.setText("Your OTP for TrustVault is: <b>" + otp + "</b> valid for the next 3 mins !!!", true);
		javaMailSender.send(mimeMessage);
	}
	
	private void storeOtpToDb(String email,String otp) {
		
		OtpDto otpDto = new OtpDto();
		otpDto.setEmail(email);
		otpDto.setOtp(otp);
		otpService.addOTP(otpDto);
		
	}

	
}
