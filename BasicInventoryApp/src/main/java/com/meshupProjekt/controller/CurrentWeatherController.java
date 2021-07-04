package com.meshupProjekt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.meshupProjekt.service.LiveWeatherService;


/*@Controller
public class CurrentWeatherController {
    @GetMapping("/current-weather")
    public String getCurrentWeather(Model model) {
        CurrentWeather currentWeather = new CurrentWeather("Clear", BigDecimal.ONE, BigDecimal.ZERO, BigDecimal.TEN);
        model.addAttribute("currentWeather", currentWeather);
        return "current-weather";
    }
}*/
@Controller
public class CurrentWeatherController {
 
    private final LiveWeatherService liveWeatherService;
 
    public CurrentWeatherController(LiveWeatherService liveWeatherService) {
        this.liveWeatherService = liveWeatherService;
    }
 
    @GetMapping("/current-weather")
    public String getCurrentWeather(Model model) {
        model.addAttribute("currentWeather", liveWeatherService.getCurrentWeather("winterthur","switzerland"));
        return "current-weather";
    }

	
}