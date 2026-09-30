package com.sntiago05.codearena.application.ports.in.result;

public record AuthenticationResult(
        String accessToken,
        String refreshToken
) {
}
