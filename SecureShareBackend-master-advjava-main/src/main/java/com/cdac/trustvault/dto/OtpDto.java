package com.cdac.trustvault.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//@Getter
//@Setter
//@ToString
public class OtpDto {
    @NotBlank(message="Email is required")
	private String email;
    //private String password;
    private String otp; // For OTP validation
	public OtpDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OtpDto(@NotBlank(message = "Email is required") String email, String otp) {
		super();
		this.email = email;
		this.otp = otp;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	@Override
	public String toString() {
		return "OtpDto [email=" + email + ", otp=" + otp + "]";
	}

    
    
    
    
}
