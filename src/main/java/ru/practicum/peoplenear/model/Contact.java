package ru.practicum.peoplenear.model;

import jakarta.persistence.*;
import lombok.*;
import ru.practicum.peoplenear.enumeration.ContactType;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    private Person person;
    @Column(name = "type_", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private ContactType contactType;
    @Column(name = "value_", nullable = false)
    private String value;
}
