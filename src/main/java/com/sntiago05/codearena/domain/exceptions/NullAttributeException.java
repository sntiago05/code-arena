package com.sntiago05.codearena.domain.exceptions;

public class NullAttributeException extends DomainException {
    public NullAttributeException(String attribute) {
        super(String.format("%s cannot be null.", attribute));
    }
}
