package ru.practicum.peoplenear.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.peoplenear.dto.PersonCreateDTO;
import ru.practicum.peoplenear.dto.PersonDTO;
import ru.practicum.peoplenear.exception.NotFoundException;
import ru.practicum.peoplenear.mapper.PersonMapper;
import ru.practicum.peoplenear.repository.PersonRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;

    @Override
    public PersonDTO createPerson(PersonCreateDTO dto) {
        var person = PersonMapper.personDTOToPerson(dto);
        person = personRepository.save(person);
        return PersonMapper.personToPersonDTO(person);
    }

    @Override
    public List<PersonDTO> findAllPersons() {
        return personRepository.findAll().stream()
                .map(PersonMapper::personToPersonDTO)
                .toList();
    }

    @Override
    public PersonDTO findPersonById(long id) {
        return personRepository.findById(id)
                .map(PersonMapper::personToPersonDTO)
                .orElseThrow(() -> new NotFoundException("Person with id=" + id + " not found"));
    }

    @Override
    public void deletePerson(long id) {
        personRepository.deleteById(id);
    }

    @Override
    public PersonDTO updatePerson(long id, PersonDTO personDTO) {

        return null;
    }
}
