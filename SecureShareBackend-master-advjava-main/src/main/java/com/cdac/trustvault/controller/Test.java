package com.cdac.trustvault.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {

	
	@GetMapping("/getalluserfromdb")
    public String getUsers() {
        return "welcome vaishnavi";
    }
}
