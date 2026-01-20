package ru.practicum.peoplenear.dto;

import lombok.Builder;
import lombok.Data;
import ru.practicum.peoplenear.enumeration.ContactType;
import ru.practicum.peoplenear.model.Person;

@Data
@Builder
public class ContactDTO {
    private long id;
    private Person person;
    private ContactType contactType;
    private String value;
}
