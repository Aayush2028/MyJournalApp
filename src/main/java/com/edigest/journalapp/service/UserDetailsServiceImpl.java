//package com.edigest.journalapp.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Component;
//
//import com.edigest.journalapp.entity.User;
//import com.edigest.journalapp.repository.UserRepo;
//
//@Component
//public class UserDetailsServiceImpl implements UserDetailsService{
//	
//	private final UserRepo userRepo;
//
//    public UserDetailsServiceImpl(UserRepo userRepo) {
//        this.userRepo = userRepo;
//    }
//	
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
//		User user = userRepo.findByUsername(username);
//		if(user != null) {
//			UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
//			.username(user.getUsername())
//			.password(user.getPassword())
//			.roles(user.getRoles().toArray(new String[0]))
//			.build();
//			return userDetails;
//		}
//		throw new UsernameNotFoundException("User not found with username: "+username);
//	}
//}
