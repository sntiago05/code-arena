package com.sntiago05.codearena.application.ports.in.command;

/**
 * Command containing credentials for user authentication.
 */
public record AuthenticateUserCommand(
        String email,
        String password
) {
}
