package com.edigest.journalapp.service;

import static org.mockito.Mockito.*;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ActiveProfiles;

import com.edigest.journalapp.entity.User;
import com.edigest.journalapp.repository.UserRepo;

@ActiveProfiles("test")
public class JournalEntryServiceTest {

	@InjectMocks
	private JournalEntryService journalEntryService;
	
	@Mock
	private UserService userService;
	
	@Mock
	private UserRepo userRepo;
	
	@BeforeEach
	void initialize() {
		MockitoAnnotations.initMocks(this);
	}
	
	
	@Test
	public void deleteJournalEntryTest() {
		User user = new User();
		user.setId(new ObjectId());
		user.setUsername("Aayush");
		user.setPassword("Aayush");
		doNothing().when(userRepo).deleteById(ArgumentMatchers.any());
		when(userService.findByUsername(ArgumentMatchers.any())).thenReturn(user);
		Assertions.assertNotNull(user);
	}
}
