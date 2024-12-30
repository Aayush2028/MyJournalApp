package com.edigest.journalapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.edigest.journalapp.cache.AppCache;
import com.edigest.journalapp.response.WeatherResponse;

import lombok.extern.slf4j.Slf4j;

//Difference between @Component and @Service.
//In service we write business logic. We can also write @Component, but if one day some other person reads your code and sees
//@Service annotation, then he/she will know that this particular class contains business logic.
@Service
@Slf4j
public class WeatherService {
	@Value("${weather.api.access.key}")
	private String apiKey;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private AppCache appCache;
	
	@Autowired
	private RedisService redisService;
	
	public WeatherResponse getWeatherDetails(String city) {
		WeatherResponse response = redisService.get("weather_of_"+city, WeatherResponse.class);
		if(response == null) {
			String weatherApi = appCache.APP_CACHE.get(AppCache.keys.weather_api.toString());
			String url = UriComponentsBuilder.fromHttpUrl(weatherApi)
	                .queryParam("access_key", apiKey)
	                .queryParam("query", city)
	                .toUriString();
			
			log.info("Url: {} and City: {}", url, city);
			WeatherResponse weatherResponse = restTemplate.getForObject(url, WeatherResponse.class);
			redisService.set("weather_of_"+city, weatherResponse, 300l);
			return weatherResponse;
		}
		return response;
	}
}
