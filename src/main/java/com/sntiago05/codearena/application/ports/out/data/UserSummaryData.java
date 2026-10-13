package com.sntiago05.codearena.application.ports.out.data;

import com.sntiago05.codearena.domain.user.UserRole;
import com.sntiago05.codearena.domain.user.UserState;

import java.util.UUID;

public record UserSummaryData(
        UUID id,
        String name,
        String email,
        UserRole role,
        UserState state
) {
}