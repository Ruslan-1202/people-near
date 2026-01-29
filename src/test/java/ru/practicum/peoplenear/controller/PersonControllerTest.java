package ru.practicum.peoplenear.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import ru.practicum.peoplenear.exception.NotFoundException;
import ru.practicum.peoplenear.model.Person;
import ru.practicum.peoplenear.repository.PersonRepository;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@AutoConfigureMockMvc
@SpringBootTest
public class PersonControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    private PersonRepository personRepository;
    public static final ObjectMapper jackson = new ObjectMapper()
            .registerModule(new JavaTimeModule());
    @BeforeEach
    void setUp() {
        personRepository.deleteAll();
    }

    @SneakyThrows
    @Test
    @DisplayName("Получить все записи")
    void shouldFindAllPersons() {
        createTestPersons();
        mockMvc.perform(get("/person"))
                .andDo(print())
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$", hasSize(2))
                );
    }

    @SneakyThrows
    @Test
    @DisplayName("Создать запись")
    void shouldCreatePerson() {
        ZonedDateTime now = ZonedDateTime.now();
        var result = mockMvc.perform(post("/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                   "name": "Test Create 1",
                                   "birthDate": "2004-05-23"
                                }
                                """)
                )
                .andDo(print())
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.id", notNullValue()),
                        jsonPath("$.name", is("Test Create 1"))
                ).andReturn();
        var id = jackson.readValue(result.getResponse().getContentAsString(), Person.class).getId();
        var person = personRepository.findById(id).orElseThrow(() -> new NotFoundException("Person not found"));
        assertFalse(now.isAfter(person.getCreationTs()), "Creation ts is wrong");
        assertNull(person.getEditTs(), "Edit ts is wrong");
    }

    @SneakyThrows
    @Test
    @DisplayName("Плохое создание записи")
    void shouldBadCreatePerson() {
        mockMvc.perform(post("/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                   "birthDate": "2004-05-23"
                                }
                                """)
                )
                .andDo(print())
                .andExpectAll(
                        status().is4xxClientError()
                );
    }

    @SneakyThrows
    @Test
    @DisplayName("Редактирование записи")
    void shouldUpdatePerson() {
        ZonedDateTime now = ZonedDateTime.now();
        var id = createTestPersons().get(0);
        mockMvc.perform(post("/person/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                 {
                                     "name": "Test Update 1",
                                     "lastName": "Test Update 2"
                                 }
                                """))
                .andDo(print())
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.name", is("Test Update 1")),
                        jsonPath("$.lastName", is("Test Update 2"))
                ).andReturn();
        var person = personRepository.findById(id).orElseThrow(() -> new NotFoundException("Person not found"));
        assertFalse(now.isAfter(person.getCreationTs()), "Creation ts is changed");
        assertTrue(person.getEditTs() != null && !now.isAfter(person.getEditTs()), "Edit ts is wrong");
    }

    @SneakyThrows
    @Test
    @DisplayName("Получить запись")
    void shouldGetByIdPerson() {
        long id = createTestPersons().get(0);
        mockMvc.perform(get("/person/" + id)
                )
                .andDo(print())
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.id", is((int) id))
                );
    }

    @SneakyThrows
    @Test
    @DisplayName("Плохое получение одной записи")
    void shouldGetByBadIdPerson() {
        mockMvc.perform(get("/person/" + 99999)
                )
                .andDo(print())
                .andExpectAll(
                        status().is4xxClientError()
                );
    }

    @SneakyThrows
    @Test
    @DisplayName("Удалить запись")
    void shouldDeletePerson() {
        var id = createTestPersons().get(0);
        mockMvc.perform(delete("/person/" + id)
                )
                .andDo(print())
                .andExpectAll(
                        status().isOk()
                );
        assertEquals(1, personRepository.count(), "Неверное количество записей после удаления");
    }

    private List<Long> createTestPersons() {
        List<Long> personIds = new ArrayList<>();
        Person person = Person.builder()
                .name("Test 1")
                .birthDate(LocalDate.of(2020, 1, 2))
                .build();
        person = personRepository.save(person);
        personIds.add(person.getId());
        person = Person.builder()
                .name("Test 2")
                .birthDate(LocalDate.of(2021, 11, 22))
                .build();
        person = personRepository.save(person);
        personIds.add(person.getId());
        return personIds;
    }
}
