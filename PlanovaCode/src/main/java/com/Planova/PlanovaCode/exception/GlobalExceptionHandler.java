// src/main/java/com/Planova/PlanovaCode/exception/GlobalExceptionHandler.java
package com.Planova.PlanovaCode.exception;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private ProblemDetail createProblemDetail(HttpStatus status, String title, String detail, HttpServletRequest req) {
        ProblemDetail problem = ProblemDetail.forStatus(status);
        problem.setTitle(title);
        problem.setDetail(detail);
        problem.setProperty("timestamp", LocalDateTime.now());
        problem.setProperty("traceId", UUID.randomUUID().toString());
        problem.setInstance(URI.create(req.getRequestURI()));
        return problem;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidationErrors(MethodArgumentNotValidException ex, HttpServletRequest req) {
        ProblemDetail problem = createProblemDetail(HttpStatus.BAD_REQUEST, "Bad validation", "Some fields don't meet the validation requirements", req);
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        problem.setProperty("errors", errors);
        return problem;
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ProblemDetail handleNotFound(EntityNotFoundException ex, HttpServletRequest req) {
        return createProblemDetail(HttpStatus.NOT_FOUND, "Resource not found", ex.getMessage(), req);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ProblemDetail handleNotFound(ResourceNotFoundException ex, HttpServletRequest req) {
        return createProblemDetail(HttpStatus.NOT_FOUND, "Resource not found", ex.getMessage(), req);
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ProblemDetail handleDuplicate(DuplicateResourceException ex, HttpServletRequest req) {
        return createProblemDetail(HttpStatus.CONFLICT, "Duplicate resource", ex.getMessage(), req);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ProblemDetail handleBadRequest(IllegalArgumentException ex, HttpServletRequest req) {
        return createProblemDetail(HttpStatus.BAD_REQUEST, "Bad request", ex.getMessage(), req);
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleGeneric(Exception ex, HttpServletRequest req) {
        return createProblemDetail(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred", ex.getMessage(), req);
    }
}
