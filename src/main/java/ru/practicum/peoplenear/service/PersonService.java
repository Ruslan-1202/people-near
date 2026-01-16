package ru.practicum.peoplenear.service;

import jakarta.validation.Valid;
import ru.practicum.peoplenear.dto.PersonCreateDTO;
import ru.practicum.peoplenear.dto.PersonDTO;

import java.util.List;

public interface PersonService {
    PersonDTO createPerson(@Valid PersonCreateDTO dto);

    List<PersonDTO> findAllPersons();

    PersonDTO findPersonById(long id);

    void deletePerson(long id);

    PersonDTO updatePerson(long id, PersonDTO personDTO);
}
