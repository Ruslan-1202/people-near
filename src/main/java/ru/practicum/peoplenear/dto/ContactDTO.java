package ru.practicum.peoplenear.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Builder;
import lombok.Data;
import ru.practicum.peoplenear.enumeration.ContactType;
import ru.practicum.peoplenear.validation.Marker;

@Data
@Builder
public class ContactDTO {
    @NotNull(groups = Marker.onUpdate.class, message = "ID unknown")
    @Null(groups = Marker.onCreate.class, message = "ID must be null")
    private Long id;
    @NotNull(groups = {Marker.onUpdate.class, Marker.onCreate.class}, message = "Person unknown")
    private Long personId;
    @NotNull(groups = {Marker.onUpdate.class, Marker.onCreate.class}, message = "Contact type unknown")
    private ContactType contactType;
    @NotBlank(groups = {Marker.onUpdate.class, Marker.onCreate.class}, message = "Value can't be empty")
    private String value;
}
