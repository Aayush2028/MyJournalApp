package com.edigest.journalapp.entity;

import java.time.LocalDateTime;
import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.edigest.journalapp.enums.Sentiment;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

//What is ORM?
//Helps developers to integrate object oriented programming with database tables.
//For example: Consider a Java class "User" and a Database table "users". ORM frameworks like Hibernate can map the fields in the User class to columns
//in the users table, making it easier to insert, update, retrieve, and delete records.

//What is JPA(Java Persistence API)?
//Provides set of rules to achieve ORM. Includes interfaces and annotations that you use in your Java classes, requires a persistence provider(ORM tools) for implementation.
//To use JPA you need a persistence provider. A persistence provider is a specific implementation of the JPA specification. 
//Examples of JPA persistence providers include Hibernate, EclipseLink, and OpenJPA. These providers implement the JPA interfaces and provide the underlying functionality
//to interact with databases.
//JPA is primarily designed for working with relational databases, where data is stored in tables with a pre-defined schema. Therefore, it is not used with MongoDB.
//Spring Data MongoDB serves as the persistence provider for MongoDB.

//Query Method DSL and Criteria API are 2 different ways to interact with a database when using Spring Data JPA or Spring Data MongoDB.

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
 