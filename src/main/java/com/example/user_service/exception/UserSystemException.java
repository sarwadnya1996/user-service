package com.example.user_service.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
@Data
public class UserSystemException extends RuntimeException{
    private HttpStatus status;
    private String detailedMessage;

    public UserSystemException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
