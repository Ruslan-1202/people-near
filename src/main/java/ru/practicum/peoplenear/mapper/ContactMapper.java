package ru.practicum.peoplenear.mapper;

import lombok.experimental.UtilityClass;
import ru.practicum.peoplenear.dto.ContactDTO;
import ru.practicum.peoplenear.dto.ContactShortDTO;
import ru.practicum.peoplenear.model.Contact;
import ru.practicum.peoplenear.model.Person;

import java.util.List;

@UtilityClass
public class ContactMapper {
    public Contact contactDTOToContact(ContactDTO dto, Person person) {
        return Contact.builder()
                .person(person)
                .contactType(dto.getContactType())
                .value(dto.getValue())
                .build();
    }

    public ContactDTO contactToContactDTO(Contact contact) {
        return ContactDTO.builder()
                .id(contact.getId())
                .contactType(contact.getContactType())
                .personId(contact.getId())
                .value(contact.getValue())
                .build();
    }

    public ContactShortDTO contactToContactShortDTO(Contact contact) {
        return ContactShortDTO.builder()
                .id(contact.getId())
                .contactType(contact.getContactType())
                .value(contact.getValue())
                .build();
    }

    public List<ContactDTO> contactListToContactDTOList(List<Contact> contacts) {
        return contacts.stream()
                .map(ContactMapper::contactToContactDTO)
                .toList();
    }

    public List<ContactShortDTO> contactsToContactShortDTOs(List<Contact> contacts) {
        return contacts.stream()
                .map(ContactMapper::contactToContactShortDTO)
                .toList();
    }
}
