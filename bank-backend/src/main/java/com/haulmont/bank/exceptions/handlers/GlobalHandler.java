package com.haulmont.bank.exceptions.handlers;

import com.haulmont.bank.exceptions.EntityNotFoundException;
import com.haulmont.bank.exceptions.OverLimitException;
import com.haulmont.bank.exceptions.responses.DefaultExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
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
    public ResponseEntity<DefaultExceptionResponse> handleAllExceptions(Exception e) {

        DefaultExceptionResponse response = new DefaultExceptionResponse();
        response.setInfo(e.getMessage());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));

        return errors;
    }
}
