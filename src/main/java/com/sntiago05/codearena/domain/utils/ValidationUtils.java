package com.sntiago05.codearena.domain.utils;

import com.sntiago05.codearena.domain.exceptions.EmptyAttributeException;
import com.sntiago05.codearena.domain.exceptions.InvalidTimeRangeException;
import com.sntiago05.codearena.domain.exceptions.NegativeAttributeException;
import com.sntiago05.codearena.domain.exceptions.NullAttributeException;
import com.sntiago05.codearena.domain.exceptions.InvalidAttributeTypeException;

import java.time.LocalDateTime;

public class ValidationUtils {

    public static void requireNonNull(Object attribute, String name) {
        if (attribute == null) throw new NullAttributeException(name);
    }

    public static void requireNonEmpty(String attribute, String name) {
        requireNonNull(attribute, name);
        if (attribute.trim().isEmpty()) throw new EmptyAttributeException(name);
    }

    public static void requireNonNegative(Integer attribute, String name) {
        requireNonNull(attribute, name);
        if (attribute < 0) throw new NegativeAttributeException(name);
    }

    public static void validateTimeOrder(LocalDateTime start, LocalDateTime end, String errorMessage) {
        if (start != null && end != null && start.isAfter(end)) throw new InvalidTimeRangeException(errorMessage);
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
                    throw new InvalidAttributeTypeException(name, "Integer", "String with value " + attribute);
                }
            }
            default -> throw new InvalidAttributeTypeException(name, "Integer", attribute.getClass().getSimpleName());

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
                throw new InvalidAttributeTypeException(name, enumType.getSimpleName(), "String with value " + attribute);
            }
        }
        throw new InvalidAttributeTypeException(name, enumType.getSimpleName(), attribute.getClass().getSimpleName());
    }
}
