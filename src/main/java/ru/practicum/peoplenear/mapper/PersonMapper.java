package ru.practicum.peoplenear.mapper;

import lombok.experimental.UtilityClass;
import ru.practicum.peoplenear.dto.PersonCreateDTO;
import ru.practicum.peoplenear.dto.PersonDTO;
import ru.practicum.peoplenear.model.Person;

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

    public Person personDTOToPerson(PersonCreateDTO dto) {
        return Person.builder()
                .name(dto.getName())
                .middleName(dto.getMiddleName())
                .lastName(dto.getLastName())
                .nickName(dto.getNickName())
                .birthDate(dto.getBirthDate())
                .build();
    }
}
