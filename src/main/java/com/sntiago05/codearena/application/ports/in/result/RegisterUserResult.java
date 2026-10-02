package com.sntiago05.codearena.application.ports.in.result;

/**
 * Result of a user registration operation.
 */
public record RegisterUserResult(
        String name,
        String email,
        Integer experience
) {
}
