package com.sermaluc.registry.service.impl;

import org.springframework.stereotype.Service;

import com.sermaluc.registry.dto.PersonDto;
import com.sermaluc.registry.entity.Person;
import com.sermaluc.registry.exception.ResourceNotFoundException;
import com.sermaluc.registry.repository.PersonRepository;
import com.sermaluc.registry.service.PersonService;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public Person createPerson(PersonDto personDto) {
        Person person = new Person();
        person.setRut(personDto.getRut());
        person.setNombre(personDto.getNombre());
        person.setApellido(personDto.getApellido());
        person.setFechaNacimiento(personDto.getFechaNacimiento());
        person.setDireccion(personDto.getDireccion());
        person.setCalle(personDto.getCalle());
        return personRepository.save(person);
    }

    @Override
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    @Override
    public Person getPersonById(Long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Person not found with id " + id));
    }

    @Override
    public Person updatePerson(Long id, PersonDto personDto) {
        Person person = getPersonById(id);
        person.setRut(personDto.getRut());
        person.setNombre(personDto.getNombre());
        person.setApellido(personDto.getApellido());
        person.setFechaNacimiento(personDto.getFechaNacimiento());
        person.setDireccion(personDto.getDireccion());
        person.setCalle(personDto.getCalle());
        return personRepository.save(person);
    }

    @Override
    public void deletePerson(Long id) {
        Person person = getPersonById(id);
        personRepository.delete(person);
    }
}