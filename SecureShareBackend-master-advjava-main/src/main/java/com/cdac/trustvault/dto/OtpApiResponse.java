package com.cdac.trustvault.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
//@NoArgsConstructor
public class OtpApiResponse {

    private boolean success; 
    private String message;
    private LocalDateTime timeStamp;
    private String role;
    private String token;
    private String name;
    private String email;
    
    
  
    public OtpApiResponse() {
		super();
		// TODO Auto-generated constructor stub
	}


	public OtpApiResponse(boolean success, String message, String role, String token,String name,String email) {
        this.success = success;
        this.message = message;
        this.token = token;
        this.role = role;
        this.name = name;
        this.email = email;
        this.timeStamp = LocalDateTime.now();
    }

  
    public OtpApiResponse(boolean success, String message, String role) {
        this.success = success;
        this.message = message;
        this.timeStamp = LocalDateTime.now();
        this.role = role;
    }


	public boolean isSuccess() {
		return success;
	}


	public void setSuccess(boolean success) {
		this.success = success;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}


	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
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
    
    
}
