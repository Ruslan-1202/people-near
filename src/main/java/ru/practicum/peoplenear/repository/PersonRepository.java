package ru.practicum.peoplenear.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.practicum.peoplenear.model.Person;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    @EntityGraph(attributePaths = "contacts")
    Optional<Person> findById(long id);
}
