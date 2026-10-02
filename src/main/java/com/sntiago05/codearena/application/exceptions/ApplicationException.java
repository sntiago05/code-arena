package com.sntiago05.codearena.application.exceptions;

/**
 * Base exception for application-specific errors.
 */
public abstract class ApplicationException extends RuntimeException {
    protected ApplicationException(String message) {
        super(message);
    }

    protected ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }
}
