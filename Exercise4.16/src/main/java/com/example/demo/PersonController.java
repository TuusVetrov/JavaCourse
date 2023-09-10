package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class PersonController {
    @Autowired
    private PersonRepository repository;

    @GetMapping("/persons")
    public Iterable<Person> getPersons() {
        return repository.findAll();
    }

    @GetMapping("/persons/{id}")
    public Optional<Person> findPersonById(@PathVariable int id) {
        return repository.findById(id);
    }

    @PostMapping("/persons")
    public Person addPerson(@RequestBody Person person) {
        repository.save(person);
        return person;
    }

    @PutMapping("/persons/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable int id, @RequestBody Person person) {
        Optional<Person> existingPerson = repository.findById(id);

        if (existingPerson.isEmpty()) {
            return new ResponseEntity<>(this.repository.save(person), HttpStatus.CREATED);
        }

        Person updatedPerson = existingPerson.get();
        updatedPerson.setFirstname(person.getFirstname());
        updatedPerson.setSurname(person.getSurname());
        updatedPerson.setLastname(person.getLastname());
        updatedPerson.setLastname(person.getLastname());

        return new ResponseEntity<>(this.repository.save(updatedPerson), HttpStatus.OK);
    }

    @DeleteMapping("/persons/{id}")
    public void deletePerson(@PathVariable int id) {
        repository.deleteById(id);
    }
}