package com.cdac.trustvault.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
//@NoArgsConstructor
public class OtpResDto extends BaseDTO{
	private String name;
	private String email;
	private String role;
	
	
	
	public OtpResDto() {
		super();
		// TODO Auto-generated constructor stub
	}


	public OtpResDto(String name, String email,String role) {
		this.name = name;
		this.email = email;
		this.role = role;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}

	
	
}
