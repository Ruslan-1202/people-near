package ru.practicum.peoplenear.dto;

import lombok.Builder;
import lombok.Data;
import ru.practicum.peoplenear.enumeration.ContactType;

@Data
@Builder
public class ContactDTO {
    private long id;
    private PersonDTO person;
    private ContactType contactType;
    private String value;
}
