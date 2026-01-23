package ru.practicum.peoplenear.controller;

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
import ru.practicum.peoplenear.enumeration.ContactType;
import ru.practicum.peoplenear.model.Contact;
import ru.practicum.peoplenear.model.Person;
import ru.practicum.peoplenear.repository.ContactRepository;
import ru.practicum.peoplenear.repository.PersonRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class ContactControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private PersonRepository personRepository;

    @BeforeEach
    void setUp() {
        contactRepository.deleteAll();
        personRepository.deleteAll();
    }

    @Test
    @DisplayName("Создание записи")
    @SneakyThrows
    void shouldCreateContact() {
        var person = getPerson();
        mockMvc.perform(post("/contact")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.format("""
                                {
                                    "personId": %s,
                                    "contactType": "EMAIL",
                                    "value": "email@email.com"
                                }
                                """, person.getId()))
                )
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.contactType", is(ContactType.EMAIL.name())),
                        jsonPath("$.value", is("email@email.com"))
                );
    }

    @Test
    @DisplayName("Плохое создание записи - нет человека")
    @SneakyThrows
    void shouldCreateContactBadNoPerson() {
        mockMvc.perform(post("/contact")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "contactType": "EMAIL",
                                    "value": "email@email.com"
                                }
                                """)
                )
                .andExpectAll(
                        status().is4xxClientError()
                );
    }

    @Test
    @DisplayName("Плохое создание записи - есть ИД")
    @SneakyThrows
    void shouldCreateContactBadIdPresent() {
        var person = getPerson();
        mockMvc.perform(post("/contact")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.format("""
                                {
                                    "id": 1,
                                    "personId": %s,
                                    "contactType": "EMAIL",
                                    "value": "email@email.com"
                                }
                                """, person.getId()))
                )
                .andExpectAll(
                        status().is4xxClientError()
                );
    }

    @Test
    @DisplayName("Изменение записи")
    @SneakyThrows
    void shouldUpdateContact() {
        var contact = createContacts().get(0);
        mockMvc.perform(post("/contact/" + contact.getPerson().getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.format("""
                                {
                                    "id": %s,
                                    "personId": %s,
                                    "contactType": "EMAIL",
                                    "value": "emailUpdate@email.com"
                                }
                                """, contact.getPerson().getId(), contact.getPerson().getId()))
                )
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.contactType", is(ContactType.EMAIL.name())),
                        jsonPath("$.value", is("emailUpdate@email.com"))
                );
    }

    @Test
    @DisplayName("Получение всех записей")
    @SneakyThrows
    void shouldFindAllContacts() {
        createContacts();
        mockMvc.perform(get("/contact"))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$", hasSize(2)),
                        jsonPath("$[0].contactType", is(ContactType.EMAIL.name())),
                        jsonPath("$[0].value", is("Test email 1")),
                        jsonPath("$[1].contactType", is(ContactType.PHONE.name())),
                        jsonPath("$[1].value", is("Test phone 1"))
                );
    }

    @Test
    @DisplayName("Получение одной записи")
    @SneakyThrows
    void shouldGetById() {
        var contact = createContacts().get(0);
        mockMvc.perform(get("/contact/" + contact.getId()))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.contactType", is(ContactType.EMAIL.name())),
                        jsonPath("$.value", is("Test email 1"))
                );
    }

    @Test
    @DisplayName("Плохое получение одной записи")
    @SneakyThrows
    void shouldGetByIdBad() {
        createContacts();
        mockMvc.perform(get("/contact/" + 9999999))
                .andExpectAll(
                        status().is4xxClientError()
                );
    }

    @Test
    @DisplayName("Получение записей по человеку")
    @SneakyThrows
    void shouldGetByPersonId() {
        var contact = createContacts().get(0);
        mockMvc.perform(get("/contact/person/" + contact.getPerson().getId()))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$", hasSize(2)),
                        jsonPath("$[0].contactType", is(ContactType.EMAIL.name())),
                        jsonPath("$[0].value", is("Test email 1"))
                );
    }

    @Test
    @DisplayName("Плохое получение записей по человеку")
    @SneakyThrows
    void shouldGetByPersonIdBad() {
        createContacts();
        mockMvc.perform(get("/contact/person/" + 9999999))
                .andExpectAll(
                        status().is4xxClientError()
                );
    }

    @Test
    @DisplayName("Удаление записи")
    @SneakyThrows
    void shouldDeleteContactById() {
        var contact = createContacts().get(0);
        mockMvc.perform(delete("/contact/" + contact.getId()))
                .andExpectAll(
                        status().isOk()
                );
        assertEquals(1, contactRepository.count(), "Неверное количество после удаления");
    }

    @Test
    @DisplayName("Удаление записей по человеку")
    @SneakyThrows
    void shouldDeleteContactByPersonId() {
        var contact = createContacts().get(0);
        mockMvc.perform(delete("/contact/person/" + contact.getPerson().getId()))
                .andExpectAll(
                        status().isOk()
                );
        assertEquals(0, contactRepository.count(), "Неверное количество после удаления");

    }

    private List<Contact> createContacts() {
        var person = getPerson();
        List<Contact> contacts = new ArrayList<>();
        contacts.add(
                contactRepository.save(
                        Contact.builder()
                                .person(person)
                                .contactType(ContactType.EMAIL)
                                .value("Test email 1")
                                .build()
                )
        );

        contacts.add(
                contactRepository.save(
                        Contact.builder()
                                .person(person)
                                .contactType(ContactType.PHONE)
                                .value("Test phone 1")
                                .build()
                )
        );
        return contacts;
    }

    private Person getPerson() {
        return personRepository.save(
                Person.builder()
                        .name("Test name 1")
                        .birthDate(LocalDate.of(2011, 1, 4))
                        .build()
        );
    }
}
