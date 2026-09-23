package com.sntiago05.codearena.domain.exceptions;

public class EmptyAttributeException extends DomainException {
    public EmptyAttributeException(String attribute) {
        super(String.format("%s cannot be empty.", attribute));
    }
}
