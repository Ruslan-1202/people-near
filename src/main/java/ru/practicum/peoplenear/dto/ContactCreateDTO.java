package ru.practicum.peoplenear.dto;

import lombok.Builder;
import lombok.Data;
import ru.practicum.peoplenear.enumeration.ContactType;

@Data
@Builder
public class ContactCreateDTO {
    private long personId;
    private ContactType contactType;
    private String value;
}
