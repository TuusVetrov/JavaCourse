package com.example.weather.controller;

import com.example.weather.config.AppConfig;
import com.example.weather.model.WeatherData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class WeatherController {
    @Autowired
    private AppConfig appConfig;
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/weather")
    public WeatherData.Main getWeatherByCity(@RequestParam String city) throws JsonProcessingException {
        String apiUrl = appConfig.getBaseUrlPath() + "?q=" + city + "&appid=" + appConfig.getOpenWeatherMapApiKey();

        return getWeatherMain(apiUrl);
    }
    @GetMapping(value = "/weather", params = {"lat", "lon"})
    public WeatherData.Main getWeatherByCoordinates(
            @RequestParam double lat,
            @RequestParam double lon
    ) throws JsonProcessingException {
        String apiUrl = appConfig.getBaseUrlPath() +"?lat=" + lat + "&lon=" + lon + "&appid="
                + appConfig.getOpenWeatherMapApiKey();

        return getWeatherMain(apiUrl);
    }

    private WeatherData.Main getWeatherMain(String apiUrl) throws JsonProcessingException {
        String json = restTemplate.getForObject(apiUrl, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        WeatherData weatherData = objectMapper.readValue(json, WeatherData.class);
        return weatherData.getMain();
    }
}
