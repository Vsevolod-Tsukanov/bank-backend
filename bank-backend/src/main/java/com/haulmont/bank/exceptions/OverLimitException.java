package com.haulmont.bank.exceptions;

public class OverLimitException extends RuntimeException {
    public OverLimitException(String message) {
        super(message);
    }
}
