package com.edigest.journalapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
	
	@Autowired
	private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        CustomAuthenticationFilter customJournalFilter = new CustomAuthenticationFilter(authManager);
//        customJournalFilter.setFilterProcessesUrl("/journal/**"); 
//        
//        CustomAuthenticationFilter customUserFilter = new CustomAuthenticationFilter(authManager);
//        customUserFilter.setFilterProcessesUrl("/user/**"); 

//        http
//            .csrf().disable()  // Disable CSRF for non-browser clients (if needed)
//            .authorizeHttpRequests()
//                .requestMatchers("/journal/**", "/user/**").authenticated()
//                .anyRequest().permitAll()
//            .and()
//            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        

    	http.csrf(csrf -> csrf.disable());
    	http
        .authorizeHttpRequests(auth -> {
            auth.requestMatchers("/journal/**", "/user/**").authenticated();
            auth.anyRequest().permitAll();
        });

    	http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
