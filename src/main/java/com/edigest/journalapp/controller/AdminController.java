//package com.edigest.journalapp.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.edigest.journalapp.entity.User;
//import com.edigest.journalapp.service.UserService;
//
//@RestController
//@RequestMapping("/admin")
//public class AdminController {
//	@Autowired
//	private UserService userService;
//	
//	@GetMapping("/all-users")
//	public ResponseEntity<?> getAllUsers(){
//		List<User> allUsers = userService.findAll();
//		if(allUsers != null && !allUsers.isEmpty()) {
//			return new ResponseEntity<>(allUsers, HttpStatus.OK);
//		}
//		return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
//	}
//}
