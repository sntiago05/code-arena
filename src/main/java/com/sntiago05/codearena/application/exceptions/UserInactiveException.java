package com.sntiago05.codearena.application.exceptions;

/**
 * Exception thrown when an operation is attempted on an inactive user.
 */
public class UserInactiveException extends ApplicationException {
    public UserInactiveException(String message) {
        super(message);
    }

    public UserInactiveException(String message, Throwable cause) {
        super(message, cause);
    }
}
