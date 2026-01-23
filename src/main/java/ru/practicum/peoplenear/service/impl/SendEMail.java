package ru.practicum.peoplenear.service.impl;

import org.springframework.stereotype.Service;
import ru.practicum.peoplenear.service.SendMessage;

@Service("sendEMAIL")
public class SendEMail implements SendMessage {
    @Override
    public String sendMessage(String message) {
        return "E-mail Sent Successfully";
    }
}
