package com.example.location.controller;

import com.example.location.model.GeoData;
import com.example.location.model.Weather;
import com.example.location.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class WeatherController {
    @Autowired
    private WeatherService weatherService;

    @GetMapping
    public ResponseEntity<GeoData> getWeather(@RequestParam String location) {
        return weatherService.getGeoDataByName(location);
    }

    @GetMapping("/weather")
    public ResponseEntity<Weather> redirectRequestWeather(@RequestParam String location) {
        return weatherService.getWeatherData(location);
    }

    @PostMapping
    public ResponseEntity<GeoData> save(@RequestBody GeoData geodata) {
        return weatherService.saveGeoData(geodata);
    }
}
