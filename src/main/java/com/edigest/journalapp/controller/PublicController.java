package com.edigest.journalapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edigest.journalapp.entity.User;
import com.edigest.journalapp.manager.CustomAuthenticationManager;
import com.edigest.journalapp.service.UserService;
import com.edigest.journalapp.utils.JwtUtil;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/public")
@Slf4j
public class PublicController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CustomAuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@GetMapping("/health-check")
	public String healthCheck() {
		return "Ok";
	}
	
	@PostMapping("/signup")
	public User signUp(@RequestBody User user) {
		return userService.saveUser(user, "new");
	}
	
	//Login controller will return a JWT token with an expiry of, lets say, few hours
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody User user) {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
			String jwt = jwtUtil.generateToken(user.getUsername());
			return new ResponseEntity<>(jwt, HttpStatus.OK);
		}
		catch(Exception ex) {
			log.error("Exception occurred while creating authentication token: {}", ex.getMessage());
			return new ResponseEntity<>("Incorrect username or password", HttpStatus.BAD_REQUEST);
		}
	}
}
