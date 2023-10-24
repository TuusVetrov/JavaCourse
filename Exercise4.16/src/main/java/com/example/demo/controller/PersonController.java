package com.example.demo.controller;

import com.example.demo.model.Message;
import com.example.demo.model.Person;
import com.example.demo.service.MessageService;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PersonController {
    @Autowired
    private PersonService personService;

    @Autowired
    private MessageService messageService;

    @GetMapping("/persons")
    public Iterable<Person> getPersons() {
        return personService.getPersons();
    }

    @GetMapping("/persons/{id}")
    public Optional<Person> findPersonById(@PathVariable int id) {
        return personService.findPersonById(id);
    }

    @PostMapping("/persons")
    public Person addPerson(@RequestBody Person person) {
        return personService.addPerson(person);
    }

    @PutMapping("/persons/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable int id, @RequestBody Person person) {
        return personService.updatePerson(id, person);
    }

    @DeleteMapping("/persons/{id}")
    public void deletePerson(@PathVariable int id) {
        personService.deletePerson(id);
    }

    @DeleteMapping("/persons/{id}/messages/{messageId}")
    public ResponseEntity<Void> deleteMessage(@PathVariable int id, @PathVariable int messageId) {
        return messageService.deleteMessageById(id, messageId);
    }

    @GetMapping("/persons/{id}/messages")
    public ResponseEntity<List<Message>> getMessagesByUserId(@PathVariable int id) {
        return messageService.getMessagesByUserId(id);
    }

    @PostMapping("/persons/{id}/messages")
    public ResponseEntity<Person> addMessage(@PathVariable int id, @RequestBody Message message) {
        return messageService.addMessageToPerson(id, message);
    }
}