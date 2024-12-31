package com.edigest.journalapp.service;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RedisService {
	@Autowired
	private RedisTemplate redisTemplate;
	
	public <T> T get(String key, Class<T> entityClass) {
		try {
			Object redisObject = redisTemplate.opsForValue().get(key);
			ObjectMapper mapper = new ObjectMapper();
			return mapper.readValue(redisObject.toString(), entityClass);
		}
		catch(Exception ex) {
			log.error("Error occurred while fetching from redis: {}", ex.getMessage());
			return null;
		}
		
	}
//	ttl->expiry time 
	public void set(String key, Object o, Long ttl) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			String jsonValue = mapper.writeValueAsString(o);
			redisTemplate.opsForValue().set(key, jsonValue, ttl, TimeUnit.SECONDS);
		}
		catch(Exception ex) {
			log.error("Error occurred while setting key, value in redis: {}", ex.getMessage());
		}
		
	}
}