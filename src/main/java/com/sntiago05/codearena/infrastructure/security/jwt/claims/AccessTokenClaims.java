package com.sntiago05.codearena.infrastructure.security.jwt.claims;

import com.sntiago05.codearena.domain.user.UserRole;

import java.util.UUID;

public record AccessTokenClaims(
        UUID userId,
        UserRole role

) {
}
