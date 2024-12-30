package com.edigest.journalapp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//We add this annotation because when we autowire a bean, it should have been created first. Spring Boot Test makes those beans same as Spring Boot Application annotation does.
@SpringBootTest
public class UserServiceTest {
	@Autowired
	private UserService userService;
	@Autowired
	private EmailService emailService;
	
	@Test
	public void testFindByUserName() {
		assertNotNull(userService.findByUsername("Aayush"));
	}
	
	@ParameterizedTest
	@CsvSource({"1,1,2","2,3,5"})
	public void test(int a, int b, int expected) {
		assertEquals(expected, a+b);
	}
	
	@Test
	public void testSendMail() {
		emailService.sendEmail("aayushsimple21@gmail.com", "Hello", "Hello");
	}
	 
}
