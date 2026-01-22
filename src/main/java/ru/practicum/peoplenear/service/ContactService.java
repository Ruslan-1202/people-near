package ru.practicum.peoplenear.service;

import ru.practicum.peoplenear.dto.ContactCreateDTO;
import ru.practicum.peoplenear.dto.ContactDTO;
import ru.practicum.peoplenear.dto.ContactShortDTO;

import java.util.List;

public interface ContactService {
    /**
     * Список всех контактов
     *
     * @return список контактов
     */
    List<ContactDTO> findAll();

    /**
     * List of person's contacts
     *
     * @param id - ID
     * @return - lst of DTOs
     */
    List<ContactShortDTO> findByPersonId(long id);

    /**
     * Get contact by ID
     *
     * @param id - id
     * @return - contact
     */
    ContactShortDTO getContactById(long id);

    /**
     * Create contact
     *
     * @param dto - DTO
     * @return DTO with ID
     */
    ContactShortDTO create(ContactCreateDTO dto);

    /**
     * Delete contact
     *
     * @param id - ID of contact
     */
    void deleteContactById(long id);

    /**
     * Delete  all contacts of person
     *
     * @param personId - person ID
     */
    void deleteContactsByPerson(long personId);
}
