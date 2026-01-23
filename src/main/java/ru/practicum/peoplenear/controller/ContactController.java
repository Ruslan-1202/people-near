package ru.practicum.peoplenear.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.peoplenear.dto.ContactDTO;
import ru.practicum.peoplenear.dto.ContactShortDTO;
import ru.practicum.peoplenear.dto.MessageDTO;
import ru.practicum.peoplenear.service.ContactService;
import ru.practicum.peoplenear.service.MessageService;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/contact")
public class ContactController {
    private final ContactService contactService;
    private final MessageService messageService;

    @PostMapping
    public ContactShortDTO createContact(@RequestBody ContactDTO dto) {
        log.info("createContact::save contact: {}", dto);
        return contactService.create(dto);
    }

    @PostMapping("/{id}")
    public ContactShortDTO updateContact(@PathVariable Long id, @RequestBody ContactDTO dto) {
        log.info("updateContact::update contact: {}", dto);
        return contactService.updateContact(id, dto);
    }

    @GetMapping()
    public List<ContactDTO> getAllContacts() {
        log.info("getAllContacts::Get contacts");
        return contactService.findAll();
    }

    @GetMapping("/{id}")
    public ContactShortDTO getContactById(@PathVariable long id) {
        log.info("getContactById::Get contact by id: {}", id);
        return contactService.getContactById(id);
    }

    @GetMapping("/person/{personId}")
    public List<ContactShortDTO> getContactsByPerson(@PathVariable long personId) {
        log.info("getContactsByPerson::Get contacts by person: {}", personId);
        return contactService.findByPersonId(personId);
    }

    @DeleteMapping("/{id}")
    public void deleteContactById(@PathVariable long id) {
        log.info("deleteContactById::Delete contact by id: {}", id);
        contactService.deleteContactById(id);
    }

    @DeleteMapping("/person/{personId}")
    public void deleteContactsByPerson(@PathVariable long personId) {
        log.info("deleteContactsByPerson::Delete contacts by person: {}", personId);
        contactService.deleteContactsByPerson(personId);
    }

    @PostMapping("/{id}/send-message")
    public String sendMessage(@PathVariable long id, @RequestBody MessageDTO dto) {
        log.info("sendMessage::Send contact: {}\nmessage={}", id, dto);
        return messageService.sendMessage(id, dto);
    }
}
