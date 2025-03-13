package com.edigest.journalapp.response;

import java.time.LocalDateTime;

import com.edigest.journalapp.enums.Sentiment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JournalEntryResponse {
	private String id;
	private String title;
	private String content;
	private LocalDateTime date;
	private Sentiment sentiment;
}
 