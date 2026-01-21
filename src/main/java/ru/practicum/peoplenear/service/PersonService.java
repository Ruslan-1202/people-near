package ru.practicum.peoplenear.service;

import jakarta.validation.Valid;
import ru.practicum.peoplenear.dto.PersonAndContactsDTO;
import ru.practicum.peoplenear.dto.PersonCreateDTO;
import ru.practicum.peoplenear.dto.PersonDTO;

import java.util.List;

public interface PersonService {
    /**
     * Создание персоны
     *
     * @param dto - персона без ИД
     * @return - персона с ИД
     */
    PersonDTO createPerson(@Valid PersonCreateDTO dto);

    /**
     * Список человеков
     *
     * @return - список человеков
     */
    List<PersonAndContactsDTO> findAllPersons();

    /**
     * Получение человека по ИД
     *
     * @param id - ид
     * @return - человек
     */
    PersonDTO findPersonById(long id);

    /**
     * Удаление записи
     *
     * @param id - ид
     */
    void deletePerson(long id);

    /**
     * Модификация записи
     *
     * @param id        - ид
     * @param personDTO - ДТО с новыми записями, поля == null не обновляются
     * @return - новая запись
     */
    PersonDTO updatePerson(long id, PersonDTO personDTO);
}
