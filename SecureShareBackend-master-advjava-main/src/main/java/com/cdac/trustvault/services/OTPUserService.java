package com.cdac.trustvault.services;


import com.cdac.trustvault.dto.AuthRequest;
import com.cdac.trustvault.dto.PasswordResetRequest;
import com.cdac.trustvault.dto.UserRespDTO;
import com.cdac.trustvault.entity.UserEntity;

public interface OTPUserService {
	
	String addNewUser(UserEntity newUser);
	
	UserRespDTO authenticate(AuthRequest dto);

	public void updatePassword(PasswordResetRequest passResDTO) ;

	

}
