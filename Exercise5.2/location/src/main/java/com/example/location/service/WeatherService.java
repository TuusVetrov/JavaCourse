package com.example.location.service;

import com.example.location.config.AppConfig;
import com.example.location.model.GeoData;
import com.example.location.model.Weather;
import com.example.location.repository.GeoDataRepository;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class WeatherService {
    @Autowired
    private AppConfig appConfig;
    @Autowired
    private GeoDataRepository repository;
    @Autowired
    private RestTemplate restTemplate;

    public ResponseEntity<GeoData> getGeoDataByName(String location) {
        Optional<GeoData> geoDataOptional = repository.findByName(location);
        return geoDataOptional.map(geoData -> new ResponseEntity<>(geoData, HttpStatus.OK))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<Weather> getWeatherData(String location) {
        Optional<GeoData> geoDataOptional = repository.findByName(location);

        if (geoDataOptional.isPresent()) {
            GeoData geoData = geoDataOptional.get();
            String url = String.format(
                    appConfig.getBaseUrlPath() + "weather?lat=%s&lon=%s",
                    geoData.getLat(),
                    geoData.getLon()
            );

            ResponseEntity<Weather> response = restTemplate.getForEntity(url, Weather.class);

            if (response.getBody() != null) {
                return response;
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    public ResponseEntity<GeoData> saveGeoData(GeoData geodata) {
        return repository.findByName(geodata.getName()).isPresent()
                ? new ResponseEntity<>(repository.findByName(geodata.getName()).get(), HttpStatus.BAD_REQUEST)
                : new ResponseEntity<>(repository.save(geodata), HttpStatus.CREATED);
    }
}
