package com.sntiago05.codearena.domain.utils;

import com.sntiago05.codearena.domain.exceptions.EmptyAttributeException;
import com.sntiago05.codearena.domain.exceptions.InvalidTimeRangeException;
import com.sntiago05.codearena.domain.exceptions.NegativeAttributeException;
import com.sntiago05.codearena.domain.exceptions.NullAttributeException;

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
}
