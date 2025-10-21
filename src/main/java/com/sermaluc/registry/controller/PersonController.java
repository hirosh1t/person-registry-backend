package com.sermaluc.registry.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sermaluc.registry.dto.PersonDto;
import com.sermaluc.registry.entity.Person;
import com.sermaluc.registry.service.PersonService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/persons")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public ResponseEntity<Person> createPerson(@Valid @RequestBody PersonDto personDto) {
        Person createdPerson = personService.createPerson(personDto);
        return new ResponseEntity<>(createdPerson, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Long id) {
        Person person = personService.getPersonById(id);
        return ResponseEntity.ok(person);
    }

    @GetMapping
    public ResponseEntity<List<Person>> getAllPersons() {
        List<Person> persons = personService.getAllPersons();
        return ResponseEntity.ok(persons);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable Long id, @Valid @RequestBody PersonDto personDto) {
        Person updatedPerson = personService.updatePerson(id, personDto);
        return ResponseEntity.ok(updatedPerson);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
        personService.deletePerson(id);
        return ResponseEntity.noContent().build();
    }
}