package com.sntiago05.codearena.application.ports.out.data;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record RefreshTokenData(@NotNull UUID userId) {
}
