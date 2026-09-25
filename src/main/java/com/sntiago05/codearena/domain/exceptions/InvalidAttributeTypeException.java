package com.sntiago05.codearena.domain.exceptions;

public class InvalidAttributeTypeException extends DomainException {
    public InvalidAttributeTypeException(String attributeName, String expectedType, String actualType) {
        super(String.format("Attribute '%s' must be of type %s, but was %s.", attributeName, expectedType, actualType));
    }
}
