package com.sntiago05.codearena.application.ports.out;

import com.sntiago05.codearena.domain.refreshtoken.RefreshToken;

import java.util.Optional;

/**
 * Port for refresh token persistence operations.
 */
public interface RefreshTokenRepositoryPort {
    RefreshToken save(RefreshToken refreshToken);
    Optional<RefreshToken> findByToken(String refreshToken);
}
