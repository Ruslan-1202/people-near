package ru.practicum.peoplenear.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO {
    private long id;
    private String name;
    private String middleName;
    private String lastName;
    private String nickName;
    private LocalDate birthDate;
}
