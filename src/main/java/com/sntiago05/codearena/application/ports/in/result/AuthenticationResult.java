package com.sntiago05.codearena.application.ports.in.result;

/**
 * Result of a successful authentication containing access and refresh tokens.
 */
public record AuthenticationResult(
        String accessToken,
        String refreshToken
) {
}
