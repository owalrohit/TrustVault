package com.cdac.trustvault.services;

import com.cdac.trustvault.dto.OtpDto;
import com.cdac.trustvault.dto.OtpReqDto;
import com.cdac.trustvault.dto.OtpResDto;
import com.cdac.trustvault.dto.PasswordResetRequest;

public interface OtpService {
	
	String addOTP(OtpDto otpDto);
	
	public OtpResDto verifyOtp(OtpDto dto);
	
	public boolean verifyResetOtp(PasswordResetRequest passwordResetRequest) ;
	

}
