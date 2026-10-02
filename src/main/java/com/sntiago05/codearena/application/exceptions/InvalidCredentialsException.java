package com.sntiago05.codearena.application.exceptions;

/**
 * Exception thrown when user credentials are invalid.
 */
public class InvalidCredentialsException extends ApplicationException {
    public InvalidCredentialsException(String message) {
        super(message);
    }
}
