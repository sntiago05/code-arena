package com.sntiago05.codearena.domain.exceptions;

public class NegativeAttributeException extends DomainException {
    public NegativeAttributeException(String attribute) {
        super(String.format("%s cannot be negative.", attribute));
    }
}
