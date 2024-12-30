package com.edigest.journalapp.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.edigest.journalapp.entity.ConfigJournalApp;
import com.edigest.journalapp.repository.ConfigJournalAppRepo;

import jakarta.annotation.PostConstruct;

@Component
public class AppCache {
	public enum keys{
		weather_api
	}
	
	@Autowired
	private ConfigJournalAppRepo configRepo;
	
	//This will work as in-memory cache  
	public Map<String, String> APP_CACHE;
	
	//This method is invoked automatically whenever Bean AppCache is created
	@PostConstruct
	public void init() {
		APP_CACHE = new HashMap<>();
		//Mongodb will be accessed only once through in-memory cache
		List<ConfigJournalApp> allConfigs = configRepo.findAll();
		for(ConfigJournalApp config:allConfigs) {
			APP_CACHE.put(config.getKey(), config.getValue());
		}
	}
}
