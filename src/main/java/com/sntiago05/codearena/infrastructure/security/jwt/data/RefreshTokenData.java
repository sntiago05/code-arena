package com.sntiago05.codearena.infrastructure.security.jwt.data;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record RefreshTokenData(@NotNull UUID userId) {
}
