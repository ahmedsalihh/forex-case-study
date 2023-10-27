package com.ahmedsalihh.forexcasestudy.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private final Logger LOGGER = LogManager.getLogger(this.getClass());

    @ExceptionHandler(NoParameterProvidedException.class)
    public ResponseEntity<?> noParameterProvidedException(NoParameterProvidedException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), 1001, request.getDescription(false));
        LOGGER.error("{}", errorDetails.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DateNotRecognizedException.class)
    public ResponseEntity<?> dateNotRecognizedException(DateNotRecognizedException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), 1002, request.getDescription(false));
        LOGGER.error("{}", errorDetails.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidCurrencyException.class)
    public ResponseEntity<?> invalidCurrencyException(InvalidCurrencyException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), 1003, request.getDescription(false));
        LOGGER.error("{}", errorDetails.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globalExceptionHandler(Exception ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), 500, request.getDescription(false));
        LOGGER.error("{}", errorDetails.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
