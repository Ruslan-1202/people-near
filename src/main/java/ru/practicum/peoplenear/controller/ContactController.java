package ru.practicum.peoplenear.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.peoplenear.dto.ContactDTO;
import ru.practicum.peoplenear.service.ContactService;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/contact")
public class ContactController {
    private final ContactService contactService;

    @GetMapping
    public List<ContactDTO> getContacts() {
        log.info("Get contacts");
        return contactService.findAll();
    }
}
