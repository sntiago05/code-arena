package com.sntiago05.codearena.domain.exceptions;

/** Base runtime exception for domain-specific errors. */
public abstract class DomainException extends RuntimeException {
    protected DomainException(String message) {
        super(message);
    }
}
