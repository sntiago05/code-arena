package com.sntiago05.codearena.application.ports.out.data;

import java.time.LocalDateTime;

public record GeneratedRefreshToken(
        String token,
        LocalDateTime expiresAt
) {
}
