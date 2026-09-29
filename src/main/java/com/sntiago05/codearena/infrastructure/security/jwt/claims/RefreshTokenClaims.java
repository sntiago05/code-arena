package com.sntiago05.codearena.infrastructure.security.jwt.claims;

import java.util.UUID;

public record RefreshTokenClaims(UUID userId) {
}
