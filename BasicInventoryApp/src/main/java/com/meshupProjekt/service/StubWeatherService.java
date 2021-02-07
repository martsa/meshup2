package com.meshupProjekt.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.meshupProjekt.model.CurrentWeather;

@Service
public class StubWeatherService {
 
    public CurrentWeather getCurrentWeather(String city, String country) {
        return new CurrentWeather("Clear", BigDecimal.ONE, BigDecimal.ZERO, BigDecimal.TEN);
    }
}