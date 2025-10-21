package com.sermaluc.registry.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sermaluc.registry.dto.PersonDto;
import com.sermaluc.registry.entity.Person;
import com.sermaluc.registry.service.PersonService;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = PersonController.class)
@AutoConfigureMockMvc(addFilters = false)
class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PersonService personService;

    @InjectMocks
    private PersonController personController;

    @Autowired
    private ObjectMapper objectMapper;

    @TestConfiguration
    static class TestConfig {
        @Bean
        PersonService personService() {
            return Mockito.mock(PersonService.class);
        }
    }

    @Test
    void testCreatePerson() throws Exception {
        PersonDto personDto = new PersonDto("12345678-9", "John", "Doe", LocalDate.parse("1990-01-01"), "123 Main St", "Metropolitana");
        Person person = new Person(null, "12345678-9", "John", "Doe", LocalDate.parse("1990-01-01"), "Santiago", "Metropolitana");

        when(personService.createPerson(any(PersonDto.class))).thenReturn(person);

        mockMvc.perform(post("/api/persons")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(personDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.rut").value(person.getRut()))
                .andExpect(jsonPath("$.nombre").value(person.getNombre()))
                .andExpect(jsonPath("$.apellido").value(person.getApellido()));
    }

    @Test
    void testGetPerson() throws Exception {
        Long id = 1L;
        Person person = new Person(id, "12345678-9", "John", "Doe", LocalDate.parse("1990-01-01"), "123 Main St", "Metropolitana");

        when(personService.getPersonById(id)).thenReturn(person);

        mockMvc.perform(get("/api/persons/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.rut").value(person.getRut()))
                .andExpect(jsonPath("$.nombre").value(person.getNombre()))
                .andExpect(jsonPath("$.apellido").value(person.getApellido()));
    }
    
    @Test
    void getAllPersons_ReturnsOkAndJsonArray() throws Exception {
        Person p1 = new Person();
        Person p2 = new Person();
        List<Person> persons = Arrays.asList(p1, p2);

        when(personService.getAllPersons()).thenReturn(persons);

        mockMvc.perform(get("/api/persons"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith("application/json"))
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void testUpdatePerson() throws Exception {
        Long id = 1L;
        PersonDto personDto = new PersonDto("12345678-9", "John", "Doe", LocalDate.parse("1990-01-01"), "123 Main St", "Metropolitana");
        Person updatedPerson = new Person(id, "12345678-9", "John", "Doe", LocalDate.parse("1990-01-01"), "456 Elm St", "Metropolitana");

        when(personService.updatePerson(eq(id), any(PersonDto.class))).thenReturn(updatedPerson);

        mockMvc.perform(put("/api/persons/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(personDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.direccion").value(updatedPerson.getDireccion()));
    }

    @Test
    void testDeletePerson() throws Exception {
        Long id = 1L;

        doNothing().when(personService).deletePerson(id);

        mockMvc.perform(delete("/api/persons/{id}", id))
                .andExpect(status().isNoContent());
    }
}