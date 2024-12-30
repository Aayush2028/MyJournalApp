package com.edigest.journalapp.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.edigest.journalapp.entity.ConfigJournalApp;


public interface ConfigJournalAppRepo extends MongoRepository<ConfigJournalApp, ObjectId>{
	ConfigJournalApp findByKey(String key);
}
