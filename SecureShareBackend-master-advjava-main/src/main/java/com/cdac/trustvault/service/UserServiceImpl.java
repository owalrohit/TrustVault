package com.cdac.trustvault.service;


import java.util.List;
import java.util.Optional;
	
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdac.trustvault.dao.UserDao;
import com.cdac.trustvault.dao.UserrRepository;
import com.cdac.trustvault.entity.UserEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UserServiceImpl implements UserService{

	   @Autowired
	   private UserDao userDao;
	
 	   @Autowired
	   private ModelMapper mapper;
 	   
 	  @Autowired
 	 private PasswordEncoder passwordEncoder;
 	
	  @Autowired
	    private UserrRepository userRepository;

	    public List<UserEntity> getAllUsers() {
	        return userRepository.findAll();
	    }

	    public Optional<UserEntity> getUserById(Long id) {
	        return userRepository.findById(id);
	    }

	    public UserEntity createUser(UserEntity user) {
	    	user.setPassword(user.getPassword()); 
	        return userRepository.save(user);
	    }

	    public UserEntity updateUser(Long id, UserEntity user) {
	        if (userRepository.existsById(id)) {
	            user.setId(id);
	            return userRepository.save(user);
	        }
	        return null;
	    }

	    public boolean deleteUser(Long id) {
	        if (userRepository.existsById(id)) {
	            userRepository.deleteById(id);
	            return true;
	        }
	        return false;
	    }
	    
	    public String hashPassword(String rawPassword) {
	        return passwordEncoder.encode(rawPassword);
	    }

	    public boolean verifyPassword(String rawPassword, String hashedPassword) {
	        return passwordEncoder.matches(rawPassword, hashedPassword);
	    }
	    
		public UserEntity getUserByEmail(String email) {
			return userRepository.findByEmail(email).orElse(null);
		}
		
		public UserEntity updateUserByEmail(String email, UserEntity userData) {
	        UserEntity existingUser = getUserByEmail(email);
	        if (existingUser != null) {
	            // Update fields – you can choose to update only allowed fields
	            existingUser.setName(userData.getName());
	            existingUser.setPhone(userData.getPhone());
	            existingUser.setBio(userData.getBio());
	            existingUser.setProfilePicture(userData.getProfilePicture());
	            // Only update password if it is provided and not blank
	            if (userData.getPassword() != null && !userData.getPassword().isEmpty()) {
	                existingUser.setPassword(hashPassword(userData.getPassword()));
	            }
	            // Optionally update role if your business logic allows it.
	            if (userData.getRole() != null) {
	                existingUser.setRole(userData.getRole());
	            }
	            return userRepository.save(existingUser);
	        }
	        return null;
	    }
		
	}
