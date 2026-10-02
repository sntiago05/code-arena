package com.sntiago05.codearena.application.exceptions;

/**
 * Exception thrown when a requested resource cannot be found.
 */
public class ResourceNotFoundException extends ApplicationException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
