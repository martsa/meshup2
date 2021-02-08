package com.meshupProjekt.controller;

import java.math.BigDecimal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.meshupProjekt.model.CurrentWeather;
import com.meshupProjekt.service.LiveWeatherService;
import com.meshupProjekt.service.StubWeatherService;


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
 
    private final StubWeatherService stubWeatherService;
    private final LiveWeatherService liveWeatherService;
 
    public CurrentWeatherController(StubWeatherService stubWeatherService, LiveWeatherService liveWeatherService) {
        this.stubWeatherService = stubWeatherService;
        this.liveWeatherService = liveWeatherService;
    }
 
    @GetMapping("/current-weather")
    public String getCurrentWeather(Model model) {
        model.addAttribute("currentWeather", liveWeatherService.getCurrentWeather("winterthur","switzerland"));
        return "current-weather";
    }

	public StubWeatherService getStubWeatherService() {
		return stubWeatherService;
	}
}