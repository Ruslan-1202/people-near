package ru.practicum.peoplenear.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.peoplenear.dto.PersonCreateDTO;
import ru.practicum.peoplenear.dto.PersonDTO;
import ru.practicum.peoplenear.service.PersonService;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController()
@RequestMapping({"/person", "/person/"})
public class PersonController {
    private final PersonService personService;

    @PostMapping
    public PersonDTO create(@RequestBody PersonCreateDTO person) {
        log.info("Creating person: {}", person);
        return personService.createPerson(person);
    }

    @GetMapping
    public List<PersonDTO> findAll() {
        log.info("Finding all persons");
        return personService.findAllPersons();
    }

    @GetMapping("/{id}")
    public PersonDTO findById(@PathVariable long id) {
        log.info("Finding person by id: {}", id);
        return personService.findPersonById(id);
    }

    @PostMapping("/{id}")
    public PersonDTO updatePerson(@PathVariable long id, @RequestBody PersonDTO personDTO) {
        log.info("Changing person with id: {}, \n{}", id, personDTO);
        return personService.updatePerson(id, personDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        log.info("Deleting person by id: {}", id);
        personService.deletePerson(id);
    }
}
