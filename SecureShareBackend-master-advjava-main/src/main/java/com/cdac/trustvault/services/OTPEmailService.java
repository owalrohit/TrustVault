package com.cdac.trustvault.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public interface OTPEmailService {

	public void sendOTP(String email);
	

}
