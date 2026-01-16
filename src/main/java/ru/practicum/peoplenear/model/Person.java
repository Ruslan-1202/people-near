package ru.practicum.peoplenear.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String middleName;
    private String lastName;
    private String nickName;
    @Column(nullable = false)
    private LocalDate birthDate;
}
