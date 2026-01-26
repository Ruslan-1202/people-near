package ru.practicum.peoplenear.service;

import ru.practicum.peoplenear.dto.MessageDTO;

public interface MessageService {
    String sendMessage(long contactId, MessageDTO dto);
}
