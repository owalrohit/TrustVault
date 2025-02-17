package com.cdac.trustvault.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@MappedSuperclass 
@Getter
@Setter
@ToString
public class BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@UpdateTimestamp
	//@CreationTimestamp
	@Column(name="created_on")
	private LocalDate createdOn;
	@UpdateTimestamp
	@Column(name="updated_on")
	private LocalDateTime updatedOn;
	public BaseEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BaseEntity(Long id, LocalDate createdOn, LocalDateTime updatedOn) {
		super();
		this.id = id;
		this.createdOn = createdOn;
		this.updatedOn = updatedOn;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDate getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(LocalDate createdOn) {
		this.createdOn = createdOn;
	}
	public LocalDateTime getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(LocalDateTime updatedOn) {
		this.updatedOn = updatedOn;
	}
	@Override
	public String toString() {
		return "BaseEntity [id=" + id + ", createdOn=" + createdOn + ", updatedOn=" + updatedOn + "]";
	}
	
	
}