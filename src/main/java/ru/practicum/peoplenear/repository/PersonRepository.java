package ru.practicum.peoplenear.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.peoplenear.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
