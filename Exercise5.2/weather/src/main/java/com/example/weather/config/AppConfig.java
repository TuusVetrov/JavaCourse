package com.example.weather.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Value("${openweathermap.apikey}")
    private String openWeatherMapApiKey;

    @Value("${base.url}")
    private String baseUrlPath;

    public String getOpenWeatherMapApiKey() {
        return openWeatherMapApiKey;
    }

    public String getBaseUrlPath() {
        return baseUrlPath;
    }
}
