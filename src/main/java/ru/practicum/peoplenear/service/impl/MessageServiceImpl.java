package ru.practicum.peoplenear.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.peoplenear.dto.MessageDTO;
import ru.practicum.peoplenear.service.ContactService;
import ru.practicum.peoplenear.service.MessageService;
import ru.practicum.peoplenear.service.SendMessage;

import java.util.Map;

@RequiredArgsConstructor
@Service
public class MessageServiceImpl implements MessageService {
    private final Map<String, SendMessage> sendMessageMap;
    private final ContactService contactService;

    @Override
    public String sendMessage(long contactId, MessageDTO dto) {
        var contact = contactService.getContactById(contactId);
        SendMessage sendMessage = sendMessageMap.get("send" + contact.getContactType());
        return sendMessage.sendMessage(dto.getMessage());
    }
}
