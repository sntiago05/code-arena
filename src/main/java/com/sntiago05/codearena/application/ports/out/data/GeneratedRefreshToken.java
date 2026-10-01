package com.sntiago05.codearena.application.ports.out.data;

import com.sntiago05.codearena.domain.refreshtoken.RefreshToken;
import java.time.LocalDateTime;
import java.util.UUID;

public record GeneratedRefreshToken(
        String token,
        LocalDateTime expiresAt
) {
    public RefreshToken toDomain(UUID userId) {
        return new RefreshToken(userId, token, LocalDateTime.now(), expiresAt);
    }
}
