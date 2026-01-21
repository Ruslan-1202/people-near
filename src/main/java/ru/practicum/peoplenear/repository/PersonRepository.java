package ru.practicum.peoplenear.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.practicum.peoplenear.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}
