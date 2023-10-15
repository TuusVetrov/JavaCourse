package com.example.weather.controller;

import com.example.weather.model.WeatherData;
import com.example.weather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {
    @Autowired
    private WeatherService weatherService;

    @GetMapping("/weather")
    public ResponseEntity<WeatherData.Main> getWeatherByCity(
            @RequestParam String city
    ) {
        return weatherService.getWeatherByCity(city);
    }

    @GetMapping(value = "/weather", params = {"lat", "lon"})
    public ResponseEntity<WeatherData.Main> getWeatherByCoordinates(
            @RequestParam double lat,
            @RequestParam double lon
    ) {
        return weatherService.getWeatherByCoordinates(lat, lon);
    }
}

