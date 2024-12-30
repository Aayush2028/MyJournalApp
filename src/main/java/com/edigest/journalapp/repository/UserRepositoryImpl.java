package com.edigest.journalapp.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.edigest.journalapp.entity.User;

@Component
public class UserRepositoryImpl {
	//MongoTemplate is configured automatically through the uri and database that we have provided in properties file
	@Autowired
	private MongoTemplate mongoTemplate;
	
	public List<User> getUsersForSentimentAnanlysis(){
		Query query = new Query();
		query.addCriteria(Criteria.where("email").exists(true));
		query.addCriteria(Criteria.where("sentimentAnalysis").is(true));
		List<User> users = mongoTemplate.find(query, User.class);
		return users;
	}
}
