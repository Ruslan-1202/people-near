package ru.practicum.peoplenear.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class PersonCreateDTO {
    private String name;
    private LocalDate birthDate;
}
