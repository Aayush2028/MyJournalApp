package com.edigest.journalapp.entity;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Document(collection = "users")
@Data
@NoArgsConstructor
public class User {
	@Id
	private ObjectId id;
	
	@Indexed(unique = true)
	@NonNull
	private String username;//indexing on username for faster access

	@NonNull
	private String password;
	
	

	@DBRef
	private List<JournalEntry> journalEntries = new ArrayList<>();//This will contain reference to entries in journal_entries table for a user
	
	private String role;
	
	private String email;
	
	private boolean sentimentAnalysis;
}
