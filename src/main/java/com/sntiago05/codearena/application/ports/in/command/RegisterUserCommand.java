package com.sntiago05.codearena.application.ports.in.command;

import com.sntiago05.codearena.domain.user.UserLevel;

/**
 * Command to register a new user.
 */
public record RegisterUserCommand(
        String name,
        String email,
        String password
) {
}
