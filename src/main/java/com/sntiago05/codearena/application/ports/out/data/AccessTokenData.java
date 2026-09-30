package com.sntiago05.codearena.application.ports.out.data;

import com.sntiago05.codearena.domain.user.UserRole;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record AccessTokenData(
        @NotNull UUID userId,@NotNull UserRole role) {
}
