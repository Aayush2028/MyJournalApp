package com.edigest.journalapp.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class RedisTest {
	@Autowired
	private RedisTemplate redisTemplate;
	//redis start method: first start wsl.exe(C//Program Files/WSL/wsl.exe
	//Then write these commands one by one: sudo service redis-server start, redis-cli
	@Disabled
	@Test
	public void testSendEmail() {
//		redisTemplate.opsForValue().set("email", "xyz@gmail.com");
		Object email = redisTemplate.opsForValue().get("email");
		int a=1;
	}
}
