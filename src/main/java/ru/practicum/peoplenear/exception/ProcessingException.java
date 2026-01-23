package ru.practicum.peoplenear.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public class ProcessingException extends RuntimeException {
    private final String message;
    private final HttpStatusCode statusCode;

    public ProcessingException(String message) {
        super(message);
        this.message = message;
        this.statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public ProcessingException(String message, HttpStatusCode statusCode) {
        super(message);
        this.message = message;
        this.statusCode = statusCode;
    }
}
