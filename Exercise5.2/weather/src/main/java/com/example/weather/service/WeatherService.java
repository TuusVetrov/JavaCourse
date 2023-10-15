package com.example.weather.service;

import com.example.weather.config.AppConfig;
import com.example.weather.model.WeatherData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {
    @Autowired
    private AppConfig appConfig;
    @Autowired
    private RestTemplate restTemplate;

    public ResponseEntity<WeatherData.Main> getWeatherByCity(String city) {
        String apiUrl = appConfig.getBaseUrlPath() + "?q=" + city + "&appid=" + appConfig.getOpenWeatherMapApiKey();

        return getWeatherResponse(apiUrl);
    }

    public ResponseEntity<WeatherData.Main> getWeatherByCoordinates(
            double lat,
            double lon
    ) {
        String apiUrl = appConfig.getBaseUrlPath() + "?lat=" + lat + "&lon=" + lon + "&appid=" +
                appConfig.getOpenWeatherMapApiKey();

        return getWeatherResponse(apiUrl);
    }

    private ResponseEntity<WeatherData.Main> getWeatherResponse(String apiUrl) {
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);

            WeatherData.Main weatherMain = parseWeatherData(response.getBody());
            if (weatherMain != null) {
                return ResponseEntity.ok(weatherMain);
            }
        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                return ResponseEntity.notFound().build();
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    private WeatherData.Main parseWeatherData(String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        WeatherData weatherData = objectMapper.readValue(json, WeatherData.class);
        return weatherData.getMain();
    }
}