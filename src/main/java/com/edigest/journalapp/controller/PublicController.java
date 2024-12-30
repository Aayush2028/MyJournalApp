package com.edigest.journalapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edigest.journalapp.entity.User;
import com.edigest.journalapp.service.UserService;

@RestController
@RequestMapping("/public")
public class PublicController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/health-check")
	public String healthCheck() {
		return "Ok";
	}
	
	@PostMapping("/create-user")
	public User createUser(@RequestBody User user) {
		return userService.saveUser(user, "new");
	}
}
