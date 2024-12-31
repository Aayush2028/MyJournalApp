package com.edigest.journalapp.scheduler;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.edigest.journalapp.cache.AppCache;
import com.edigest.journalapp.entity.JournalEntry;
import com.edigest.journalapp.entity.User;
import com.edigest.journalapp.enums.Sentiment;
import com.edigest.journalapp.repository.UserRepositoryImpl;
import com.edigest.journalapp.service.EmailService;

@Component
public class UserScheduler {

	@Autowired
	private EmailService emailService;
	
	@Autowired
	private UserRepositoryImpl userRepositoryImpl;
	
	@Autowired
	private AppCache appCache;

	@Scheduled(cron = "0 0 9 * * SUN")
	public void fetchUsersAndSendMail() {
		List<User> users = userRepositoryImpl.getUsersForSentimentAnanlysis();
		for(User user:users) { 
			List<JournalEntry> journalEntriesForTheUser = user.getJournalEntries();
			List<Sentiment> filteredSentiments = journalEntriesForTheUser.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x -> x.getSentiment()).collect(Collectors.toList());
			Map<Sentiment, Integer> sentimentCounts = new HashMap<>();
			int maxCount=0;
			Sentiment mostFrequentSentiment=null;
			for(Sentiment sentiment:filteredSentiments) {
				if(sentiment != null) {
					sentimentCounts.put(sentiment, sentimentCounts.getOrDefault(sentiment, 0)+1);
					int currCount = sentimentCounts.get(sentiment);
					if(currCount > maxCount) {
						maxCount = currCount;
						mostFrequentSentiment = sentiment;
					}
				}
			}
			if(mostFrequentSentiment!=null) {
				emailService.sendEmail(user.getEmail(), "Sentiment for last 7 Days", mostFrequentSentiment.toString());
			}
		}
	}
	
	@Scheduled(cron = "0 0/10 * ? * *")
	public void clearInMemoryCache() {
		appCache.init();
	}
}
