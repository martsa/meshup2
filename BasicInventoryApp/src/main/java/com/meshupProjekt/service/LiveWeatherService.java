package com.meshupProjekt.service;

import java.math.BigDecimal;
import java.net.URI;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meshupProjekt.model.CurrentWeather;
import org.springframework.web.util.UriTemplate;


@Service
public class LiveWeatherService {

    private static final String WEATHER_URL = "http://api.openweathermap.org/data/2.5/weather?q={city},{country}&APPID={key}&units=metric";

    private final String apiKey="526a647fcd4f3f465c0340c19d26ef3a";

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public LiveWeatherService(RestTemplateBuilder restTemplateBuilder, ObjectMapper objectMapper) {
        this.restTemplate = restTemplateBuilder.build();
        this.objectMapper = objectMapper;
    }

    public CurrentWeather getCurrentWeather(String city, String country) {
        URI url = new UriTemplate(WEATHER_URL).expand(city,country,apiKey);
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        return convert(response);
    }

    private CurrentWeather convert(ResponseEntity<String> response) {
        try {
            JsonNode root = objectMapper.readTree(response.getBody());
            return new CurrentWeather(root.path("weather").get(0).path("main").asText(),
                    BigDecimal.valueOf(root.path("main").path("temp").asDouble()),
                    BigDecimal.valueOf(root.path("main").path("feels_like").asDouble()),
                    BigDecimal.valueOf(root.path("wind").path("speed").asDouble()));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error parsing JSON", e);
        }
    }
}