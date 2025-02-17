package com.cdac.trustvault.services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdac.trustvault.custom_exceptions.ResourceNotFoundException;
import com.cdac.trustvault.dto.OtpDto;
import com.cdac.trustvault.dto.OtpResDto;
import com.cdac.trustvault.dto.PasswordResetRequest;
import com.cdac.trustvault.entity.Otp;
import com.cdac.trustvault.entity.UserEntity;
import com.cdac.trustvault.repository.OtpRepository;
import com.cdac.trustvault.repository.UserRoleRepo;

@Service
@Transactional
public class OtpServiceImpl implements OtpService {

	@Autowired
	OtpRepository otpRepository;

	@Autowired
	UserRoleRepo userRepository;

	@Autowired
	private ModelMapper mapper;

	@Override
	public String addOTP(OtpDto otpDto) {
		System.out.println("Adding OTP for email: " + otpDto.getEmail() + " and otp: " + otpDto.getOtp());
		Otp otp = new Otp();
		otp.setEmail(otpDto.getEmail());
		otp.setOtp(otpDto.getOtp());
		otp.setGenerationTime(LocalDateTime.now());
		otp.setExpirationTime(otp.getGenerationTime().plusMinutes(3));
		Otp otp2 = otpRepository.save(otp);
		return "otp sending done !!";
	}

	@Override
	public OtpResDto verifyOtp(OtpDto dto) {
	    Optional<Otp> optionalOtp = otpRepository.findLatestByEmail(dto.getEmail());
	    Otp otp = optionalOtp.orElseThrow(() -> new ResourceNotFoundException("Invalid email or OTP!"));
	    if (!otp.getOtp().trim().equals(dto.getOtp().trim())) {
	        throw new ResourceNotFoundException("Invalid email or OTP!");
	    }
	    UserEntity user = userRepository.findByEmail(dto.getEmail())
	            .orElseThrow(() -> new ResourceNotFoundException("Invalid email"));
	    otp.setRole(user.getRole().toString());

	    if (otp.getExpirationTime().isBefore(LocalDateTime.now())) {
	        throw new ResourceNotFoundException("OTP has expired!");
	    }

	    otpRepository.delete(otp);
	    return mapper.map(otp, OtpResDto.class);
	}


	
	@Override
	public boolean verifyResetOtp(PasswordResetRequest passwordResetRequest) {
	    Optional<Otp> optional = otpRepository.findLatestByEmail(passwordResetRequest.getEmail()); 
	    
	    if (optional.isEmpty() || !optional.get().getOtp().equals(passwordResetRequest.getOtp())) {
	        return false; 
	    }
	    
	    if (optional.get().getExpirationTime().isBefore(LocalDateTime.now())) { 
	        otpRepository.delete(optional.get());
	        return false;
	    }
	    
	    UserEntity user = userRepository.findByEmail(passwordResetRequest.getEmail())
	            .orElseThrow(() -> new ResourceNotFoundException("Invalid email or OTP!"));
	    
	    otpRepository.delete(optional.get()); 

	    return user.getEmail() != null;
	}


}
