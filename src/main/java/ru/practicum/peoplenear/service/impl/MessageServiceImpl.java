package ru.practicum.peoplenear.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.peoplenear.dto.MessageDTO;
import ru.practicum.peoplenear.exception.ProcessingException;
import ru.practicum.peoplenear.service.ContactService;
import ru.practicum.peoplenear.service.MessageService;
import ru.practicum.peoplenear.service.SendMessage;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class MessageServiceImpl implements MessageService {
    private final Map<String, SendMessage> sendMessageMap;
    private final ContactService contactService;

    @Override
    public String sendMessage(long contactId, MessageDTO dto) {
        return getService(
                contactService.getContactById(contactId).getContactType().toString()
        ).sendMessage(dto.getMessage());
    }

    private SendMessage getService(String contactType) {
        SendMessage sendMessage = sendMessageMap.get("send" + contactType);
        if (sendMessage == null) {
            log.error("SendMessage service not found for {}", contactType);
            throw new ProcessingException("Error sending message");
        }
        return sendMessage;
    }
}
