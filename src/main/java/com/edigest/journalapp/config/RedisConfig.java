package com.edigest.journalapp.config;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
	@Value("${spring.redis.uri}")
    private String redisUri;
//	We will write a Bean for RedisTemplate so that whatever update we make from our code in redis, it will be reflected from redis-cli and vice-versa.
//	By default it does not work since the serializer/de-serializer of redis and our code is different.
//	RedisConnectionFactory is an Interface to establish/manage connection with redis server.
	@Bean
	public RedisTemplate redisTemplate(RedisConnectionFactory factory) {
		
		RedisTemplate redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(factory);
		
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setValueSerializer(new StringRedisSerializer());
		
		return redisTemplate;
	}

    @Bean
    public RedisConnectionFactory redisConnectionFactory() throws URISyntaxException {
        URI uri = new URI(redisUri);
        String host = uri.getHost();
        int port = uri.getPort();
        String password = uri.getUserInfo().split(":")[1];

        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        configuration.setHostName(host);
        configuration.setPort(port);
        configuration.setPassword(password);

        return new LettuceConnectionFactory(configuration);
    }
}