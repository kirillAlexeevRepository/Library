package com.kirillalekseev.spring.security.exception_handling;

public class NotAvailableStatusException extends RuntimeException{
    public NotAvailableStatusException(String message) {
        super(message);
    }
}
