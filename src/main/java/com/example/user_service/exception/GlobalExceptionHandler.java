package com.example.user_service.exception;

import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.concurrent.CompletionException;

@RestControllerAdvice
public class GlobalExceptionHandler {


    public ResponseEntity<ErrorResponse> handleUserException(UserSystemException ex) {

        ErrorResponse response = new ErrorResponse(
                ex.getStatus().value(),
                ex.getStatus().getReasonPhrase(),
                ex.getMessage(),
                LocalDateTime.now()
        );

        return ResponseEntity
                .status(ex.getStatus())
                .body(response);
    }

    @ExceptionHandler(CompletionException.class)
    public ResponseEntity<?> handleCompletionException(CompletionException ex) {

        Throwable cause = ex.getCause();

        if (cause instanceof UserSystemException userEx) {
            return handleUserException(userEx);
        }

        ErrorResponse response = new ErrorResponse(
                500,
                "INTERNAL_SERVER_ERROR",
                cause.getMessage(),
                LocalDateTime.now()
        );

        return ResponseEntity.internalServerError().body(response);
    }
    @ExceptionHandler
    public ResponseEntity<?> handleJwtException(JWTVerificationException jwtVerificationException){
        ErrorResponse response=new ErrorResponse(401,
                "UNAUTHORIZED",
                jwtVerificationException.getMessage(),
                LocalDateTime.now());
        return ResponseEntity.status(401).body(response);
    }
}
