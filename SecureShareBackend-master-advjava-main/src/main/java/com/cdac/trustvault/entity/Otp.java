package com.cdac.trustvault.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name = "otp")
//@NoArgsConstructor
@Getter
@Setter
@ToString
public class Otp {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@CreationTimestamp
	@Column(name="generated_on")
	private LocalDateTime generationTime;
	
	@Column(name="expired_on")
	private LocalDateTime expirationTime;
	
	@Column(name="otp")
	private String otp;
	
	@Column(name="email")
	private String email;
	
	private String role;

	
	public Otp() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Otp(String otp, String email) {
		this.otp = otp;
		this.email = email;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public LocalDateTime getGenerationTime() {
		return generationTime;
	}


	public void setGenerationTime(LocalDateTime generationTime) {
		this.generationTime = generationTime;
	}


	public LocalDateTime getExpirationTime() {
		return expirationTime;
	}


	public void setExpirationTime(LocalDateTime expirationTime) {
		this.expirationTime = expirationTime;
	}


	public String getOtp() {
		return otp;
	}


	public void setOtp(String otp) {
		this.otp = otp;
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


	@Override
	public String toString() {
		return "Otp [id=" + id + ", generationTime=" + generationTime + ", expirationTime=" + expirationTime + ", otp="
				+ otp + ", email=" + email + ", role=" + role + "]";
	}
	
	
}
	
	
