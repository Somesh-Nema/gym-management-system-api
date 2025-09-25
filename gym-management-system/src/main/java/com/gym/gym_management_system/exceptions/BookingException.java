package com.gym.gym_management_system.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST) // Tells Spring to return a 400 status
public class BookingException extends RuntimeException {
    public BookingException(String message) {
        super(message);
    }
}
