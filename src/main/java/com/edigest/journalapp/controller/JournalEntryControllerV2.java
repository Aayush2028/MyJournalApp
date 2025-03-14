package com.edigest.journalapp.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edigest.journalapp.entity.JournalEntry;
import com.edigest.journalapp.entity.User;
import com.edigest.journalapp.request.JournalEntryRequest;
import com.edigest.journalapp.response.JournalEntryResponse;
import com.edigest.journalapp.service.JournalEntryService;
import com.edigest.journalapp.service.UserService;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {
	
	@Autowired
	private JournalEntryService journalEntryService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	public ResponseEntity<List<JournalEntryResponse>> getAllJournalEntriesOfUser(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		User user = userService.findByUsername(username);
		List<JournalEntry> entries = user.getJournalEntries();
		List<JournalEntryResponse> response = entries.stream().map(entry -> new JournalEntryResponse(entry.getId().toString(), entry.getTitle(),entry.getContent(), entry.getDate(), entry.getSentiment())).collect(Collectors.toList());
				
		if(entries != null && !entries.isEmpty()) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
	}
	
	@PostMapping
	public ResponseEntity<?> createEntry(@RequestBody JournalEntryRequest entry) {
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String username = auth.getName();
//			entry.setDate(LocalDateTime.now());
			JournalEntry entryToBeSaved = new JournalEntry();
			entryToBeSaved.setTitle(entry.getTitle());
			entryToBeSaved.setContent(entry.getContent());
			entryToBeSaved.setSentiment(entry.getSentiment());
			entryToBeSaved.setDate(LocalDateTime.now());
			JournalEntry entryInDb = journalEntryService.saveEntry(entryToBeSaved, username);
			return new ResponseEntity<>(new JournalEntryResponse(entryInDb.getId().toString(),
					entryInDb.getTitle(), 
					entryInDb.getContent(), 
					entryInDb.getDate(), 
					entryInDb.getSentiment()), 
					HttpStatus.CREATED);
		}
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/get-by-id/{id}")
	public ResponseEntity<JournalEntry> getById(@PathVariable ObjectId id){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		User user = userService.findByUsername(username);
		List<JournalEntry> collect = user.getJournalEntries().stream().filter(x -> x.getId().equals(id)).collect(Collectors.toList());
		if(!collect.isEmpty()) {
			Optional<JournalEntry> journalEntry = journalEntryService.findEntryById(id);
			if(journalEntry.isPresent()) {
				return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/delete-by-id/{id}")
	public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId id) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		journalEntryService.deleteById(id, username);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("/update-by-id/{id}")
	public ResponseEntity<?> updateEntryById( @PathVariable ObjectId id, @RequestBody JournalEntryRequest updatedEntry) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		User user = userService.findByUsername(username);
		List<JournalEntry> collect = user.getJournalEntries().stream().filter(x -> x.getId().equals(id)).collect(Collectors.toList());
		if(!collect.isEmpty()) {
			JournalEntry journalEntry = journalEntryService.findEntryById(id).orElse(null);
			if(journalEntry != null) {
				journalEntry.setTitle(updatedEntry.getTitle() != null?updatedEntry.getTitle():journalEntry.getTitle());
				journalEntry.setContent(updatedEntry.getContent() != null?updatedEntry.getContent():journalEntry.getContent());
				journalEntryService.saveEntry(journalEntry);
				return new ResponseEntity<>(new JournalEntryResponse(journalEntry.getId().toString(),
						journalEntry.getTitle(), 
						journalEntry.getContent(), 
						journalEntry.getDate(), 
						journalEntry.getSentiment()), 
						HttpStatus.OK);
			}
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
} 
