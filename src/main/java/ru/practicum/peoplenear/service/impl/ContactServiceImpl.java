package ru.practicum.peoplenear.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.peoplenear.dto.ContactCreateDTO;
import ru.practicum.peoplenear.dto.ContactDTO;
import ru.practicum.peoplenear.dto.ContactShortDTO;
import ru.practicum.peoplenear.exception.NotFoundException;
import ru.practicum.peoplenear.mapper.ContactMapper;
import ru.practicum.peoplenear.repository.ContactRepository;
import ru.practicum.peoplenear.service.ContactService;
import ru.practicum.peoplenear.service.PersonService;

import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class ContactServiceImpl implements ContactService {
    private final ContactRepository contactRepository;
    private final PersonService personService;

    @Transactional
    @Override
    public ContactShortDTO create(ContactCreateDTO dto) {
        var person = personService.findEntityById(dto.getPersonId());
        var contact = ContactMapper.contactCreateDTOToContact(dto, person);
        return ContactMapper.contactToContactShortDTO(contactRepository.save(contact));
    }

    @Override
    public List<ContactDTO> findAll() {
        return contactRepository.findAll().stream()
                .map(ContactMapper::contactToContactDTO)
                .toList();
    }

    @Override
    public ContactShortDTO getContactById(long id) {
        return contactRepository.findById(id)
                .map(ContactMapper::contactToContactShortDTO)
                .orElseThrow(() -> new NotFoundException("Contact id=" + id + " not found"));
    }

    @Override
    public List<ContactShortDTO> findByPersonId(long id) {
        return ContactMapper.contactsToContactShortDTOs(contactRepository.findAllByPersonId(id));
    }

    @Override
    public void deleteContactById(long id) {
        contactRepository.deleteById(id);
    }

    @Override
    public void deleteContactsByPerson(long personId) {
        contactRepository.deleteAllByPersonId(personId);
    }
}
