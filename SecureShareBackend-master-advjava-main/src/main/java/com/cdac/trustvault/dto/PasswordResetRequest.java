package com.cdac.trustvault.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
//@NoArgsConstructor
//@AllArgsConstructor
public class PasswordResetRequest {

	
	@NotBlank(message = "Email is requied!")
	@Email(message="Invalid Email format")
	private String email;
    private String otp;
    @NotNull(message="Password must be supplied")
	@Pattern(regexp="((?=.*\\d)(?=.*[a-z])(?=.*[#@$*]).{5,20})",message = "Invalid password format!!!!")
    private String newPassword;
	public PasswordResetRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PasswordResetRequest(
			@NotBlank(message = "Email is requied!") @Email(message = "Invalid Email format") String email, String otp,
			@NotNull(message = "Password must be supplied") @Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[#@$*]).{5,20})", message = "Invalid password format!!!!") String newPassword) {
		super();
		this.email = email;
		this.otp = otp;
		this.newPassword = newPassword;
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
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
    
    
    
}
