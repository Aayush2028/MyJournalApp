package com.edigest.journalapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.edigest.journalapp.entity.User;
import com.edigest.journalapp.repository.UserRepo;

@Service
public class UserService {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	
	public User saveUser(User user, String userStatus) {
		if(userStatus.equals("new") || userStatus.equals("update_existing")) {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
		}
		return userRepo.save(user);
	}
	
	public List<User> findAll(){
		return userRepo.findAll();
	}
	
	public Optional<User> findUserById(ObjectId id) {
		return userRepo.findById(id);
	}
	
	public void deleteUser(String username) {
		User user = userRepo.findByUsername(username);
		List<ObjectId> journalIds = new ArrayList<>();
		user.getJournalEntries().forEach(entry -> journalIds.add(entry.getId()));
		userRepo.deleteByUsername(username);
//		journalEntryService.deleteAllById(journalIds);
	}
	
	public User findByUsername(String username) {
		return userRepo.findByUsername(username);
	}
}
