package com.sntiago05.codearena.application.ports.in.command;

/**
 * Command to refresh an authentication token.
 */
public record RefreshTokenCommand(
        String token
) {
}
