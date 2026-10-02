package com.sntiago05.codearena.application.exceptions;

/**
 * Exception thrown when a JWT token is invalid.
 */
public class InvalidTokenException extends ApplicationException {
    public InvalidTokenException(String message) {
        super(message);
    }
}
