package com.cdac.trustvault.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import com.cdac.trustvault.entity.UserRole;
import jakarta.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "users") // to specify name of the table
//@NoArgsConstructor
//@AllArgsConstructor
@Getter
@Setter
@ToString(callSuper = true, exclude = { "password" })

public class UserEntity extends BaseEntity {


	@Column(length = 20) 
	private String name;
	

	@Column(length = 50, unique = true) 
	private String email;
	
	@Column(length = 500, nullable = false)
	private String password;
	
	@Pattern(regexp = "\\d{10}", message = "Phone number must be 10 digits")
    private String phone;

	
	@Enumerated(EnumType.STRING) 
	@Column(length = 30) 
	private UserRole role;

	@Column(length = 500)
    private String bio;
    
    @Column(columnDefinition = "LONGTEXT")
    private String profilePicture;
	public UserEntity() {
		super();
		// TODO Auto-generated constructor stub
	}


	public UserEntity(String name, String email, String password,
			@Pattern(regexp = "\\d{10}", message = "Phone number must be 10 digits") String phone, UserRole role) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.phone = phone;
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


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public UserRole getRole() {
		return role;
	}


	public void setRole(UserRole role) {
		this.role = role;
	}


	public String getBio() {
		return bio;
	}


	public void setBio(String bio) {
		this.bio = bio;
	}


	public String getProfilePicture() {
		return profilePicture;
	}


	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}
 
   
	@Override
	public String toString() {
		return "UserEntity [name=" + name + ", email=" + email + ", password=" + password + ", phone=" + phone
				+ ", role=" + role + "]";
	}
	
	
    

}
