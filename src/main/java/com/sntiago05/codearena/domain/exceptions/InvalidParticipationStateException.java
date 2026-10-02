package com.sntiago05.codearena.domain.exceptions;

/** Exception thrown when an operation is attempted from an invalid participation state. */
public class InvalidParticipationStateException extends DomainException {
    public InvalidParticipationStateException(String message) {
        super(message);
    }
}
