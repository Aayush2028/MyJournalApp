//package com.edigest.journalapp.controller;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.edigest.journalapp.entity.JournalEntry;
//
////It is a special type of Component.
//@RestController
//@RequestMapping("/_journal")
//public class JournalEntryController {
//	private Map<Long, JournalEntry> journalEntries = new HashMap<>();
//	
//	@GetMapping
//	public List<JournalEntry> getAll(){
//		return new ArrayList<>(journalEntries.values());
//		
//	}
//	
//	@PostMapping
//	public boolean createEntry(@RequestBody JournalEntry entry) {
//		try {
//			journalEntries.put( entry.getId(), entry);
//			return true;
//		}
//		catch(Exception e) {
//			return false;
//		}
//	}
//	
//	@GetMapping("/get-by-id/{id}")
//	public JournalEntry getById(@PathVariable Long id){
//		return journalEntries.get(id);
//	}
//	
//	@DeleteMapping("/delete-by-id/{id}")
//	public JournalEntry deleteJournalEntryById(@PathVariable Long id) {
//		return journalEntries.remove(id);
//	}
//	
//	@PutMapping("/update-by-id/{id}")
//	public JournalEntry updateEntryById(@PathVariable Long id, @RequestBody JournalEntry entry) {
//		return journalEntries.put(id, entry);
//	}
//} 
