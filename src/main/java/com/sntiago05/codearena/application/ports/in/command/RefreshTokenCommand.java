package com.sntiago05.codearena.application.ports.in.command;

import jakarta.validation.constraints.NotBlank;

/**
 * Command to refresh an authentication token.
 */
public record RefreshTokenCommand(
        @NotBlank(message = "Token cannot be blank")
        String token
) {
}
