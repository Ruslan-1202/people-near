package ru.practicum.peoplenear.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Builder;
import lombok.Data;
import org.apache.logging.log4j.util.Strings;

import java.time.LocalDate;

@Data
@Builder
public class PersonCreateDTO {
    private String name;
    private String middleName;
    private String lastName;
    private String nickName;
    @PastOrPresent(message = "Дата рождения должна быть в прошлом")
    private LocalDate birthDate;

    @AssertTrue(message = "Имя или фамилия или прозвище должно быть задано")
    public boolean isNameNotEmpty() {
        return Strings.isNotBlank(name) || Strings.isNotBlank(nickName) || Strings.isNotBlank(lastName);
    }
}
