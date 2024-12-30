package com.edigest.journalapp.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.edigest.journalapp.entity.JournalEntry;

//We have to find journal entry using string id from the table.
public interface JournalEntryRepo extends MongoRepository<JournalEntry, ObjectId>{
	
}
