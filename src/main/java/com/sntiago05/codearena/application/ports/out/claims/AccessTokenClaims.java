package com.sntiago05.codearena.application.ports.out.claims;

import com.sntiago05.codearena.domain.user.UserRole;

import java.util.UUID;

/**
 * Claims extracted from an access token.
 */
public record AccessTokenClaims(
        UUID userId,
        UserRole role

) {
}
