package ru.practicum.peoplenear.dto;

import lombok.Builder;
import lombok.Data;
import ru.practicum.peoplenear.enumeration.ContactType;

@Data
@Builder
public class ContactShortDTO {
    private long id;
    private ContactType contactType;
    private String value;
}
