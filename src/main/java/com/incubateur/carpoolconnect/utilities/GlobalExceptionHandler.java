package com.incubateur.carpoolconnect.utilities;

import com.incubateur.carpoolconnect.utilities.exceptions.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Set;

@RestControllerAdvice
@SuppressWarnings("unused")
public class GlobalExceptionHandler {

    @ExceptionHandler(ObjectNotValidException.class)
    @SuppressWarnings("unused")
    public ResponseEntity<Set<String>> handleException(ObjectNotValidException exception) {
        return ResponseEntity
                .badRequest()
                .body(exception.getErrorMessages());
    }

    @ExceptionHandler(IllegalStateException.class)
    @SuppressWarnings("unused")
    public ResponseEntity<String> handleException(IllegalStateException exception) {
        return ResponseEntity
                .badRequest()
                .body(exception.getMessage());
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    @SuppressWarnings("unused")
    public ResponseEntity<String> handleException(SQLIntegrityConstraintViolationException exception) {
        return ResponseEntity
                .badRequest()
                .body("Un utilisateur utilisant cette adresse mail existe déjà");
    }

    @SuppressWarnings("unused")
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleException(EntityNotFoundException exception) {
        return ResponseEntity
                .notFound()
                .build();
    }

    @ExceptionHandler(UserDisabledException.class)
    @SuppressWarnings("unused")
    public ResponseEntity<String> handleException(UserDisabledException exception) {
        return ResponseEntity
                .badRequest()
                .body(exception.getMessage());
    }

    @ExceptionHandler(AuthenticationException.class)
    @SuppressWarnings("unused")
    public ResponseEntity<String> handleException(AuthenticationException exception) {
        return ResponseEntity
                .badRequest()
                .body(exception.getMessage());
    }

    @ExceptionHandler(IncorrectOldPasswordException.class)
    @SuppressWarnings("unused")
    public ResponseEntity<String> handleException(IncorrectOldPasswordException exception) {
        return ResponseEntity
                .badRequest()
                .body(exception.getMessage());
    }

    @ExceptionHandler(RouteFullException.class)
    @SuppressWarnings("unused")
    public ResponseEntity<String> handleException(RouteFullException exception) {
        return ResponseEntity
                .badRequest()
                .body(exception.getMessage());
    }

    @ExceptionHandler(NotDriverException.class)
    @SuppressWarnings("unused")
    public ResponseEntity<String> handleException(NotDriverException exception) {
        return ResponseEntity
                .badRequest()
                .body(exception.getMessage());
    }

}
