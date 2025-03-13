package com.edigest.journalapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edigest.journalapp.entity.User;
import com.edigest.journalapp.repository.UserRepo;
import com.edigest.journalapp.response.WeatherResponse;
import com.edigest.journalapp.service.UserService;
import com.edigest.journalapp.service.WeatherService;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private UserService userService;
	@Autowired
	private WeatherService weatherService;
	
	@GetMapping
	public ResponseEntity<WeatherResponse> greeting(@RequestParam String city){
		return new ResponseEntity<>(weatherService.getWeatherDetails(city), HttpStatus.OK);
	}
	
	@PutMapping("/update-user")
	public ResponseEntity<?> updateUser(@RequestBody User user) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userName = auth.getName();
		
		User userInDb = userService.findByUsername(userName);
		userInDb.setUsername(user.getUsername());
		userInDb.setPassword(user.getPassword());
		userService.saveUser(userInDb, "update_existing");
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("/delete-user")
	public ResponseEntity<?> deleteUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userName = auth.getName();
		
		userRepo.deleteByUsername(userName);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT); 
	}
}
