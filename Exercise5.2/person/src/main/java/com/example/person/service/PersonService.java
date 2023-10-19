package com.example.person.service;

import com.example.person.config.AppConfig;
import com.example.person.model.Person;
import com.example.person.model.Weather;
import com.example.person.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class PersonService {
    @Autowired
    private AppConfig appConfig;
    @Autowired
    private PersonRepository repository;
    @Autowired
    private RestTemplate restTemplate;

    public ResponseEntity<Weather> getWeatherForPerson(int id) {
        Optional<Person> personOptional = repository.findById(id);

        if (personOptional.isPresent()) {
            Person person = personOptional.get();
            String location = person.getLocationName();
            String url = String.format(
                    appConfig.getBaseUrlPath() + "weather?location=" + location
            );

            try {
                return restTemplate.getForEntity(url, Weather.class);
            } catch (HttpClientErrorException ex) {
                if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                    return ResponseEntity.notFound().build();
                }
            }
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<Iterable<Person>> findAllPeople() {
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<Person> findPersonById(int id) {
        return repository.findById(id).isPresent()
                ? new ResponseEntity<>(repository.findById(id).get(), HttpStatus.OK)
                : ResponseEntity.notFound().build();
    }

    public ResponseEntity<Person> savePerson(Person person) {
        return repository.findById(person.getId()).isPresent()
                ? new ResponseEntity<>(repository.findById(person.getId()).get(), HttpStatus.BAD_REQUEST)
                : new ResponseEntity<>(repository.save(person), HttpStatus.CREATED);
    }
}
