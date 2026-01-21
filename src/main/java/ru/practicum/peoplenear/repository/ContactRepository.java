package ru.practicum.peoplenear.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.practicum.peoplenear.model.Contact;

import java.util.List;
import java.util.Set;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    @EntityGraph(attributePaths = "person")
    List<Contact> findAll();

    List<Contact> findAllByPersonId(long id);

    List<Contact> findAllByPersonIdIn(Set<Long> ids);

    void deleteAllByPersonId(long id);
}
