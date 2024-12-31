package com.edigest.journalapp.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class RedisTest {
	@Autowired
	private RedisTemplate redisTemplate;

	@Disabled
	@Test
	public void testSendEmail() {
//		redisTemplate.opsForValue().set("email", "xyz@gmail.com");
		Object email = redisTemplate.opsForValue().get("email");
		int a=1;
	}
}
