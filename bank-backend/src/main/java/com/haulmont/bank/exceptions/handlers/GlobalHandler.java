package com.haulmont.bank.exceptions.handlers;

import com.haulmont.bank.exceptions.EntityNotFoundException;
import com.haulmont.bank.exceptions.OverLimitException;
import com.haulmont.bank.exceptions.responses.DefaultExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalHandler {

    @ExceptionHandler
    public ResponseEntity<DefaultExceptionResponse> handleOverLimit(OverLimitException e) {

        DefaultExceptionResponse response = new DefaultExceptionResponse();
        response.setInfo(e.getMessage());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<DefaultExceptionResponse> handleEntityNotFound(EntityNotFoundException e) {

        DefaultExceptionResponse response = new DefaultExceptionResponse();
        response.setInfo(e.getMessage());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<DefaultExceptionResponse> handleEntityNotFound(Exception e) {

        DefaultExceptionResponse response = new DefaultExceptionResponse();
        response.setInfo(e.getMessage());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
