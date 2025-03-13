//package com.edigest.journalapp.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class WebConfig implements WebMvcConfigurer {
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**") // Apply CORS to all endpoints
//                .allowedOriginPatterns("http://localhost:*","file:///C:/Users/aayus/JournalFrontend/login.html") // Replace with your frontend's origin
//                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Include OPTIONS for preflight
//                .allowedHeaders("*") // Allow all headers
//                .allowCredentials(true); // Allow cookies or Authorization headers
//    }
//}
