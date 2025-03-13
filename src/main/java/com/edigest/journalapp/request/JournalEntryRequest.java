package com.edigest.journalapp.request;

import java.time.LocalDateTime;

import com.edigest.journalapp.enums.Sentiment;
import com.edigest.journalapp.response.JournalEntryResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JournalEntryRequest {
	private String id;
	private String title;
	private String content;
	private LocalDateTime date;
	private Sentiment sentiment;
}
