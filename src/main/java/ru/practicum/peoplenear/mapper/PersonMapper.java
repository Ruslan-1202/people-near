package ru.practicum.peoplenear.mapper;

import lombok.experimental.UtilityClass;
import ru.practicum.peoplenear.dto.PersonAndContactsDTO;
import ru.practicum.peoplenear.dto.PersonCreateDTO;
import ru.practicum.peoplenear.dto.PersonDTO;
import ru.practicum.peoplenear.model.Contact;
import ru.practicum.peoplenear.model.Person;

import java.util.List;

@UtilityClass
public class PersonMapper {
    public PersonDTO personToPersonDTO(Person person) {
        return PersonDTO.builder()
                .id(person.getId())
                .name(person.getName())
                .middleName(person.getMiddleName())
                .lastName(person.getLastName())
                .nickName(person.getNickName())
                .birthDate(person.getBirthDate())
                .build();
    }

    public Person personCreateDTOToPerson(PersonCreateDTO dto) {
        return Person.builder()
                .name(dto.getName())
                .middleName(dto.getMiddleName())
                .lastName(dto.getLastName())
                .nickName(dto.getNickName())
                .birthDate(dto.getBirthDate())
                .build();
    }

    public Person personDTOToPerson(PersonDTO dto) {
        return Person.builder()
                .id(dto.getId())
                .name(dto.getName())
                .middleName(dto.getMiddleName())
                .lastName(dto.getLastName())
                .nickName(dto.getNickName())
                .birthDate(dto.getBirthDate())
                .build();
    }

    public PersonAndContactsDTO personToPersonAndContactsDTO(Person person, List<Contact> contacts) {
        return PersonAndContactsDTO.builder()
                .id(person.getId())
                .name(person.getName())
                .middleName(person.getMiddleName())
                .lastName(person.getLastName())
                .nickName(person.getNickName())
                .birthDate(person.getBirthDate())
                .contacts(ContactMapper.contactsToContactShortDTOs(contacts))
                .build();
    }

    public PersonAndContactsDTO personToPersonAndContactsDTO(Person person) {
        return PersonAndContactsDTO.builder()
                .id(person.getId())
                .name(person.getName())
                .middleName(person.getMiddleName())
                .lastName(person.getLastName())
                .nickName(person.getNickName())
                .birthDate(person.getBirthDate())
                .contacts(ContactMapper.contactsToContactShortDTOs(person.getContacts()))
                .build();
    }
}
