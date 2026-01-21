package ru.practicum.peoplenear.service;

import ru.practicum.peoplenear.dto.ContactDTO;

import java.util.List;

public interface ContactService {
    /**
     * Список всех контактов
     *
     * @return список контактов
     */
    List<ContactDTO> findAll();

    List<ContactDTO> findByPersonId(long id);
}
