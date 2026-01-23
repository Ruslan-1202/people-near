package ru.practicum.peoplenear.service.impl;

import org.springframework.stereotype.Service;
import ru.practicum.peoplenear.service.SendMessage;

@Service("sendPHONE")
public class SendSMS implements SendMessage {
    @Override
    public String sendMessage(String message) {
        return message + ", SMS sent";
    }
}
