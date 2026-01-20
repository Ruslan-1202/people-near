package ru.practicum.peoplenear.service;

import ru.practicum.peoplenear.dto.ContactDTO;

import java.util.List;

public interface ContactService {
    List<ContactDTO> findAll();
}
