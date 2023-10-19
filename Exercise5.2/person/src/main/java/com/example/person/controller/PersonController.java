package com.example.person.controller;

import com.example.person.model.Person;
import com.example.person.model.Weather;
import com.example.person.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    private PersonService personService;

    @GetMapping("{id}/weather")
    public ResponseEntity<Weather> getWeather(@PathVariable int id) {
        return personService.getWeatherForPerson(id);
    }

    @GetMapping
    public ResponseEntity<Iterable<Person>> findAll() {
        return personService.findAllPeople();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> findById(@PathVariable int id) {
        return personService.findPersonById(id);
    }

    @PostMapping
    public ResponseEntity<Person> save(@RequestBody Person person) {
        return personService.savePerson(person);
    }
}

