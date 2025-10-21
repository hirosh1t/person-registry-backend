package com.sermaluc.registry.service;

import java.util.List;

import com.sermaluc.registry.dto.PersonDto;
import com.sermaluc.registry.entity.Person;

public interface PersonService {
	Person createPerson(PersonDto personDto);
	List<Person> getAllPersons();
	Person getPersonById(Long id);
	Person updatePerson(Long id, PersonDto personDto);
    void deletePerson(Long id);
}