package com.edigest.journalapp.service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edigest.journalapp.entity.JournalEntry;
import com.edigest.journalapp.entity.User;
import com.edigest.journalapp.repository.JournalEntryRepo;

@Service
public class JournalEntryService{
	@Autowired
	private JournalEntryRepo journalEntryRepo;
	@Autowired
	private UserService userService;
	
	private static final Logger logger = LoggerFactory.getLogger(JournalEntryService.class);
	
	//Execute this method as a single operation/portion so that there is no inconsistency, i.e..., data gets saved in both the tables simultaneously
	//It is mandatory to have replication in mongodb to run a Transaction
	@Transactional
	public JournalEntry saveEntry(JournalEntry entry, String username) {
		User user = userService.findByUsername(username);
		//If a document with same entry id is present in the table then, instead of creating a new entry, the existing one will be updated.
		JournalEntry entryInDb = journalEntryRepo.save(entry);
		user.getJournalEntries().add(entryInDb);
		userService.saveUser(user, "existing");
		
		logger.info("JournalEntry with id {} saved in journal_entries for user: {}", entryInDb.getId(), username);
		return entryInDb;
	}
	
	public JournalEntry saveEntry(JournalEntry entry) {
		return journalEntryRepo.save(entry);
	}
	
	public List<JournalEntry> findAll(){
		return journalEntryRepo.findAll();
	}
	
	public Optional<JournalEntry> findEntryById(ObjectId id) {
		return journalEntryRepo.findById(id);
	}
	
	public void deleteById(ObjectId id, String username) {
		User user = userService.findByUsername(username);
		user.getJournalEntries().removeIf(x -> x.getId().equals(id));
		userService.saveUser(user, "existing");
		journalEntryRepo.deleteById(id);
	}
	
	public void deleteAllById(List<ObjectId> ids) {
		journalEntryRepo.deleteAllById(ids);
	}
}
