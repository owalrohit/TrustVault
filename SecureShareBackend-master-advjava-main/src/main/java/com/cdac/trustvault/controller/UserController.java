package com.cdac.trustvault.controller;

import com.cdac.trustvault.dao.UserrRepository;
import com.cdac.trustvault.dto.AuthRequest;
import com.cdac.trustvault.dto.OtpDto;
import com.cdac.trustvault.dto.UserRespDTO;
import com.cdac.trustvault.entity.UserEntity;
import com.cdac.trustvault.service.UserServiceImpl;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {
	
	  @Autowired
	    private UserServiceImpl userService;
	  
		 @GetMapping(path="/getalluser") public List<UserEntity> getUsers() { return
		  userService.getAllUsers(); }
		 
	
	    @GetMapping("/{id}")
	    public ResponseEntity<UserEntity> getUser(@PathVariable Long id) {
	        Optional<UserEntity> user = userService.getUserById(id);
	        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	    }

 
		  @PostMapping(path="/create")
		    public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity user) {
			  user.setPassword(userService.hashPassword(user.getPassword()));
			  UserEntity createdUser = userService.createUser(user);
		        return ResponseEntity.status(201).body(createdUser);
		    }
		
	    

	    @PutMapping("/update/{id}")
	    public ResponseEntity<UserEntity> updateUser(@PathVariable Long id, @RequestBody UserEntity user) {
	    	UserEntity updatedUser = userService.updateUser(id, user);
	        return updatedUser != null ? ResponseEntity.ok(updatedUser) : ResponseEntity.notFound().build();
	    }

	    @DeleteMapping("/delete/{id}")
	    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
	        return userService.deleteUser(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
	    }
	    
	 // Get a user by email: GET /users?email=user@example.com
	    @GetMapping("/by-email")
	    public ResponseEntity<UserEntity> getUserByEmail(@RequestParam("email") String email) {
	        UserEntity user = userService.getUserByEmail(email);
	        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
	    }

	    // Update a user by email: PUT /users?email=user@example.com
	    @PutMapping("/by-email")
	    public ResponseEntity<UserEntity> updateUserByEmail(
	            @RequestParam("email") String email,
	            @RequestBody UserEntity userData) {
	        UserEntity updatedUser = userService.updateUserByEmail(email, userData);
	        return updatedUser != null ? ResponseEntity.ok(updatedUser) : ResponseEntity.notFound().build();
	    }


	}    	