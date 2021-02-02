package com.meshupProjekt.controller;

import java.math.BigDecimal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.meshupProjekt.model.CurrentWeather;

@Controller
public class CurrentWeatherController {
    @GetMapping("/current-weather")
    public String getCurrentWeather(Model model) {
        CurrentWeather currentWeather = new CurrentWeather("Clear", BigDecimal.ONE, BigDecimal.ZERO, BigDecimal.TEN);
        model.addAttribute("currentWeather", currentWeather);
        return "current-weather";
    }
}

