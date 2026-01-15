package ru.practicum.peoplenear.mapper;

import lombok.experimental.UtilityClass;
import ru.practicum.peoplenear.dto.PersonCreateDTO;
import ru.practicum.peoplenear.dto.PersonDTO;
import ru.practicum.peoplenear.model.Person;

@UtilityClass
public class PersonMapper {
    public PersonDTO personToPersonDTO(Person person) {
        return new PersonDTO(
                person.getId(),
                person.getName(),
                person.getBirthDate()
        );
    }

    public Person personDTOToPerson(PersonCreateDTO dto) {
        return Person.builder()
                .name(dto.getName())
                .birthDate(dto.getBirthDate())
                .build();
    }
}
