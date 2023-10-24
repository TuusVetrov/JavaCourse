package com.example.demo.service;

import com.example.demo.repository.PersonRepository;
import com.example.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonService {
    @Autowired
    PersonRepository repository;

    public Iterable<Person> getPersons() {
        return repository.findAll();
    }

    public Optional<Person> findPersonById(int id) {
        return repository.findById(id);
    }

    public Person addPerson(Person person) {
        repository.save(person);
        return person;
    }

    public ResponseEntity<Person> updatePerson(int id, Person person) {
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

    public void deletePerson(int id) {
        repository.deleteById(id);
    }
}
