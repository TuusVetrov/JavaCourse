package com.example.location.controller;

import com.example.location.config.AppConfig;
import com.example.location.model.GeoData;
import com.example.location.model.Weather;
import com.example.location.repository.GeoDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@RestController
public class WeatherController {
    @Autowired
    private AppConfig appConfig;
    @Autowired
    private GeoDataRepository repository;
    @Autowired
    private RestTemplate restTemplate;
    @GetMapping
    public Optional<GeoData> getWeather(@RequestParam String location) {
        return repository.findByName(location);
    }

    @GetMapping("/weather")
    public ResponseEntity redirectRequestWeather(@RequestParam String location) {
        Optional<GeoData> geoDataOptional = repository.findByName(location);
        if(geoDataOptional.isPresent()) {
            GeoData geoData = geoDataOptional.get();
            String url = String.format(
                    appConfig.getBaseUrlPath() + "weather?lat=%s&lon=%s",
                    geoData.getLat(),
                    geoData.getLon()
            );

            Weather data = restTemplate.getForObject(url, Weather.class);
            return new ResponseEntity(data, HttpStatus.OK);
        } else {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public GeoData save(@RequestBody GeoData geodata) {
        return repository.save(geodata);
    }
}