package com.edigest.journalapp.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.edigest.journalapp.entity.User;

public interface UserRepo extends MongoRepository<User, ObjectId>{
	User findByUsername(String username);
	
	@Query("{username:'?0'}")
    User findUserByUsername(String username);
	
	void deleteByUsername(String username);
}
