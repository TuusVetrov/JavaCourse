package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MessageService {
    @Autowired
    PersonRepository repository;

    public ResponseEntity<Void> deleteMessageById(int personId, int messageId) {
        Optional<Person> person = repository.findById(personId);

        if (person.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Person recipient = person.get();

        Optional<Message> messageOptional = recipient.getMessages().stream()
                .filter(message -> message.getId() == messageId)
                .findFirst();

        if (messageOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Message messageToRemove = messageOptional.get();
        recipient.getMessages().remove(messageToRemove);

        repository.save(recipient);

        return ResponseEntity.ok().build();
    }

    public ResponseEntity<List<Message>> getMessagesByUserId(int personId) {
        Optional<Person> person = repository.findById(personId);

        return person.map(value ->
                new ResponseEntity<>(value.getMessages(), HttpStatus.OK)).orElseGet(() ->
                ResponseEntity.badRequest().build()
        );
    }

    public ResponseEntity<Person> addMessageToPerson(int personId, Message message) {
        Optional<Person> person = repository.findById(personId);

        if (person.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Person recipient = person.get();

        message.setPerson(recipient);
        message.setTime(LocalDateTime.now());
        recipient.addMessage(message);

        return new ResponseEntity<>(repository.save(recipient), HttpStatus.OK);
    }
}