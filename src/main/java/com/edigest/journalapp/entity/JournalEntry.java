package com.edigest.journalapp.entity;

import java.time.LocalDateTime;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.edigest.journalapp.enums.Sentiment;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;


@Document(collection = "journal_entries")
@Data
@NoArgsConstructor
public class JournalEntry {
	@Id
	private ObjectId id;//primary key
	@NonNull
	private String title;
	private String content;
	private LocalDateTime date;
	private Sentiment sentiment;
}
 