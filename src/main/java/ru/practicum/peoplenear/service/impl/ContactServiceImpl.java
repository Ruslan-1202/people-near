package ru.practicum.peoplenear.service.impl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import ru.practicum.peoplenear.dto.ContactDTO;
import ru.practicum.peoplenear.dto.ContactShortDTO;
import ru.practicum.peoplenear.exception.NotFoundException;
import ru.practicum.peoplenear.exception.ProcessingException;
import ru.practicum.peoplenear.mapper.ContactMapper;
import ru.practicum.peoplenear.model.Contact;
import ru.practicum.peoplenear.repository.ContactRepository;
import ru.practicum.peoplenear.service.ContactService;
import ru.practicum.peoplenear.service.PersonService;
import ru.practicum.peoplenear.validation.Marker;

import java.util.List;

@Validated
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class ContactServiceImpl implements ContactService {
    private final ContactRepository contactRepository;
    private final PersonService personService;

    @Transactional
    @Validated({Marker.onCreate.class})
    @Override
    public ContactShortDTO create(@Valid ContactDTO dto) {
        var person = personService.findEntityById(dto.getPersonId());
        var contact = ContactMapper.contactDTOToContact(dto, person);
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
        return ContactMapper.contactToContactShortDTO(getContactEntityById(id));
    }

    @Override
    public List<ContactShortDTO> findByPersonId(long id) {
        personService.findEntityById(id);
        return ContactMapper.contactsToContactShortDTOs(contactRepository.findAllByPersonId(id));
    }

    @Transactional
    @Override
    public void deleteContactById(long id) {
        contactRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void deleteContactsByPerson(long personId) {
        contactRepository.deleteAllByPersonId(personId);
    }

    @Transactional
    @Override
    public ContactShortDTO updateContact(Long id, ContactDTO dto) {
        var contact = getContactEntityById(id);
        if (dto.getValue() != null) {
            contact.setValue(dto.getValue());
        } else {
            throw new ProcessingException("Only values can be updated");
        }
        return ContactMapper.contactToContactShortDTO(contactRepository.save(contact));
    }

    private Contact getContactEntityById(long id) {
        return contactRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Contact id=" + id + " not found"));
    }
}
