package com.edigest.journalapp.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserRepositoryImplTest {
	
	@Autowired
	private UserRepositoryImpl userRepositoryImpl;
	
	@Test
	public void testGetUsersForSentimentAnanlysis() {
		assertNotNull(userRepositoryImpl.getUsersForSentimentAnanlysis());
	}
}
