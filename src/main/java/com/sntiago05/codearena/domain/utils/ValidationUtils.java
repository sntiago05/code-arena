package com.sntiago05.codearena.domain.utils;

import com.sntiago05.codearena.domain.exceptions.InvalidAttributeException;
import java.time.LocalDateTime;

/**
 * Domain-specific utility methods for attribute validation.
 */
public class ValidationUtils {

    public static void requireNonNull(Object attribute, String name) {
        if (attribute == null) throw new InvalidAttributeException(String.format("%s cannot be null.", name));
    }

    public static void requireNonEmpty(String attribute, String name) {
        requireNonNull(attribute, name);
        if (attribute.trim().isEmpty()) throw new InvalidAttributeException(String.format("%s cannot be empty.", name));
    }

    public static void requireNonNegative(Integer attribute, String name) {
        requireNonNull(attribute, name);
        if (attribute < 0) throw new InvalidAttributeException(String.format("%s cannot be negative.", name));
    }

    public static void validateTimeOrder(LocalDateTime start, LocalDateTime end, String errorMessage) {
        if (start != null && end != null && start.isAfter(end)) throw new InvalidAttributeException(errorMessage);
    }

    public static Integer requireInteger(Object attribute, String name) {
        requireNonNull(attribute, name);
        switch (attribute) {
            case Integer i -> {
                return i;
            }
            case Number number -> {
                return number.intValue();
            }
            case String s -> {
                try {
                    return Integer.parseInt(s);
                } catch (NumberFormatException e) {
                    throw new InvalidAttributeException(String.format("Attribute '%s' must be of type Integer, but was String with value %s.", name, attribute));
                }
            }
            default -> throw new InvalidAttributeException(String.format("Attribute '%s' must be of type Integer, but was %s.", name, attribute.getClass().getSimpleName()));
        }
    }

    public static <T extends Enum<T>> T requireEnumType(Object attribute, Class<T> enumType, String name) {
        requireNonNull(attribute, name);
        if (enumType.isInstance(attribute)) {
            return enumType.cast(attribute);
        }
        if (attribute instanceof String) {
            try {
                return Enum.valueOf(enumType, (String) attribute);
            } catch (IllegalArgumentException e) {
                throw new InvalidAttributeException(String.format("Attribute '%s' must be of type %s, but was String with value %s.", name, enumType.getSimpleName(), attribute));
            }
        }
        throw new InvalidAttributeException(String.format("Attribute '%s' must be of type %s, but was %s.", name, enumType.getSimpleName(), attribute.getClass().getSimpleName()));
    }
}
