package com.cdac.trustvault.dto;

import com.cdac.trustvault.entity.UserRole;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
//@NoArgsConstructor
public class UserRespDTO extends BaseDTO {
	
	private String name;
	private String email;
	private UserRole role;
	
	
	public UserRespDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserRespDTO(String name, String email) {
		super();
		this.name = name;
		this.email = email;
	}
	
	UserRespDTO(String Jwt) {
		
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

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}
	
	
}
