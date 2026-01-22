package ru.practicum.peoplenear.enumeration;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ContactType {
    PHONE("phone number"),
    EMAIL("e-mail");

    private final String description;
}
