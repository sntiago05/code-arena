package com.sntiago05.codearena.application.exceptions;

public class UserInactiveException extends ApplicationException {
    public UserInactiveException(String message) {
        super(message);
    }

    public UserInactiveException(String message, Throwable cause) {
        super(message, cause);
    }
}
