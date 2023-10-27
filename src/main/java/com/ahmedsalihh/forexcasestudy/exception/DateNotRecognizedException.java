package com.ahmedsalihh.forexcasestudy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class DateNotRecognizedException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public DateNotRecognizedException(String message) {
        super(message);
    }
}
