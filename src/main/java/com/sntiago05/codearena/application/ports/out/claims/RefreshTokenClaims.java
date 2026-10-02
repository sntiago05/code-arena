package com.sntiago05.codearena.application.ports.out.claims;

import java.util.UUID;

/**
 * Claims extracted from a refresh token.
 */
public record RefreshTokenClaims(UUID userId) {
}
