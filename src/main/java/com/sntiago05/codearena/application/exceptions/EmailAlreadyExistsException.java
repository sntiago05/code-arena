package com.sntiago05.codearena.application.exceptions;

/**
 * Exception thrown when attempting to use an email address that is already registered.
 */
public class EmailAlreadyExistsException extends ApplicationException {
    public EmailAlreadyExistsException(String message) {
        super(message);
    }
}
