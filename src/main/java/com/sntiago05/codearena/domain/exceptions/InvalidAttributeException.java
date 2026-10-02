package com.sntiago05.codearena.domain.exceptions;

/**
 * Exception thrown when a domain attribute is invalid.
 */
public class InvalidAttributeException extends DomainException {
    public InvalidAttributeException(String message) {
        super(message);
    }
}
