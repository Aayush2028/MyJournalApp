package com.edigest.journalapp.response;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherResponse{
    private Current current;
    
    @Data
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    private class Current{
    	@JsonProperty("observation_time")
    	private String observationTime;
    	
        private int temperature;
        
        @JsonProperty("weather_code")
        private int weatherCode;
        
        @JsonProperty("weather_icons")
        private ArrayList<String> weatherIcons;
        
        @JsonProperty("weather_descriptions")
        private ArrayList<String> weatherDescriptions;
        
        @JsonProperty("wind_speed")
        private int windSpeed;
        
        @JsonProperty("wind_degree")
        private int windDegree;
        
        @JsonProperty("wind_dir")
        private String windDir;
        
        private int pressure;
        
        private int precip;
        
        private int humidity;
        
        private int cloudcover;
        
        private int feelslike;
    }
}


