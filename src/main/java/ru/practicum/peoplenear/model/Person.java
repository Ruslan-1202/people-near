package ru.practicum.peoplenear.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;

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
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "person_id")
    private List<Contact> contacts;
    @Column(name = "creation_ts", updatable = false)
    @Builder.Default
    private ZonedDateTime creationTs = ZonedDateTime.now();
}
