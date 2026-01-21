package ru.practicum.peoplenear.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.peoplenear.dto.ContactDTO;
import ru.practicum.peoplenear.mapper.ContactMapper;
import ru.practicum.peoplenear.repository.ContactRepository;
import ru.practicum.peoplenear.service.ContactService;

import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class ContactServiceImpl implements ContactService {
    private final ContactRepository contactRepository;

    @Override
    public List<ContactDTO> findAll() {
        return contactRepository.findAll().stream()
                .map(ContactMapper::contactToContactDTO)
                .toList();
    }

    @Override
    public List<ContactDTO> findByPersonId(long id) {
        return ContactMapper.contactListToContactDTOList(contactRepository.findAllByPersonId(id));
    }
}
