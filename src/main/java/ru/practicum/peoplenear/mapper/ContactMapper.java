package ru.practicum.peoplenear.mapper;

import lombok.experimental.UtilityClass;
import ru.practicum.peoplenear.dto.ContactDTO;
import ru.practicum.peoplenear.model.Contact;

@UtilityClass
public class ContactMapper {
    public ContactDTO contactToContactDTO(Contact contact) {
        return ContactDTO.builder()
                .id(contact.getId())
                .contactType(contact.getContactType())
                .person(PersonMapper.personToPersonDTO(contact.getPerson()))
                .value(contact.getValue())
                .build();
    }
}
