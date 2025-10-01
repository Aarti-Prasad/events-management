package com.aarti.eventsmanagement.exceptions;

import com.aarti.eventsmanagement.dtos.response.APIErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Handles validation errors in @RequestBody DTOs 1234567
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<APIErrorResponse> handleValidationException(
            MethodArgumentNotValidException ex,
            HttpServletRequest request) {

        List<String> details = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.toList());

        APIErrorResponse errorResponse = new APIErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Validation Failed",
                "Input validation failed",
                request.getRequestURI(),
                details
        );

        return ResponseEntity.badRequest().body(errorResponse);
    }

    // Handles validation errors in query parameters / path variables
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<APIErrorResponse> handleConstraintViolationException(
            ConstraintViolationException ex,
            HttpServletRequest request) {

        List<String> details = ex.getConstraintViolations()
                .stream()
                .map(cv -> cv.getPropertyPath() + ": " + cv.getMessage())
                .collect(Collectors.toList());

        APIErrorResponse errorResponse = new APIErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Constraint Violation",
                "Invalid parameters",
                request.getRequestURI(),
                details
        );

        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(DuplicateEventNameException.class)
    public ResponseEntity<APIErrorResponse> handleDuplicateEventName(
            DuplicateEventNameException ex,
            HttpServletRequest request) {

        APIErrorResponse errorResponse = new APIErrorResponse(
                HttpStatus.CONFLICT.value(),
                "Duplicate Event",
                ex.getMessage(),
                request.getRequestURI(),
                List.of("Event name must be unique")
        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    // Catch-all exception handler
    @ExceptionHandler(Exception.class)
    public ResponseEntity<APIErrorResponse> handleAllExceptions(
            Exception ex,
            HttpServletRequest request) {

        APIErrorResponse errorResponse = new APIErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Internal Server Error",
                ex.getMessage(),
                request.getRequestURI(),
                List.of("An unexpected error occurred")
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}
