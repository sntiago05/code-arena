package com.sntiago05.codearena.infrastructure.security.jwt;

import com.sntiago05.codearena.domain.user.UserRole;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record AccessTokenData(
        @NotNull UUID userId,@NotNull UserRole role) {
}
