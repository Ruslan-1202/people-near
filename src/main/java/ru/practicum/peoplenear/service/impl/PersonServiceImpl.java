package ru.practicum.peoplenear.service.impl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import ru.practicum.peoplenear.dto.PersonAndContactsDTO;
import ru.practicum.peoplenear.dto.PersonCreateDTO;
import ru.practicum.peoplenear.dto.PersonDTO;
import ru.practicum.peoplenear.exception.NotFoundException;
import ru.practicum.peoplenear.mapper.PersonMapper;
import ru.practicum.peoplenear.model.Contact;
import ru.practicum.peoplenear.model.Person;
import ru.practicum.peoplenear.repository.ContactRepository;
import ru.practicum.peoplenear.repository.PersonRepository;
import ru.practicum.peoplenear.service.PersonService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Validated
@Transactional(readOnly = true)
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;
    private final ContactRepository contactRepository;

    @Transactional
    @Override
    public PersonDTO createPerson(@Valid PersonCreateDTO dto) {
        var person = PersonMapper.personCreateDTOToPerson(dto);
        person = personRepository.save(person);
        return PersonMapper.personToPersonDTO(person);
    }

    @Override
    public List<PersonAndContactsDTO> findAllPersons() {
        var persons = personRepository.findAll().stream()
                .toList();
        Map<Person, List<Contact>> contacts = contactRepository.findAllByPersonIdIn(
                        persons.stream()
                                .map(Person::getId)
                                .collect(Collectors.toSet())
                ).stream()
                .collect(Collectors.groupingBy(Contact::getPerson));
        return persons.stream()
                .map(a -> PersonMapper.personToPersonAndContactsDTO(
                                a,
                                contacts.getOrDefault(a, List.of())
                        )
                )
                .toList();
    }

    @Override
    public PersonDTO findPersonById(long id) {
        return PersonMapper.personToPersonDTO(findById(id));
    }

    @Transactional
    @Override
    public void deletePerson(long id) {
        personRepository.deleteById(id);
    }

    @Transactional
    @Override
    public PersonDTO updatePerson(long id, PersonDTO dto) {
        var person = findById(id);

        if (dto.getName() != null) {
            person.setName(dto.getName());
        }
        if (dto.getNickName() != null) {
            person.setNickName(dto.getNickName());
        }
        if (dto.getBirthDate() != null) {
            person.setBirthDate(dto.getBirthDate());
        }
        if (dto.getMiddleName() != null) {
            person.setMiddleName(dto.getMiddleName());
        }
        if (dto.getLastName() != null) {
            person.setLastName(dto.getLastName());
        }
        return PersonMapper.personToPersonDTO(personRepository.save(person));
    }

    /**
     * Получение человека по ИД
     *
     * @param id - ид
     * @return - человек
     */
    private Person findById(long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Person with id=" + id + " not found"));
    }
}
