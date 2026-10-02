package com.sntiago05.codearena.application.ports.in.result;

import com.sntiago05.codearena.domain.user.UserRole;
import com.sntiago05.codearena.domain.user.UserState;

public record UserSummaryResult(
        String name,
        String email,
        UserRole role,
        UserState state
) {
}
