package com.sntiago05.codearena.application.ports.in.result;

import com.sntiago05.codearena.domain.user.UserLevel;

public record GetMyProfileResult(
        String name,
        String email,
        Integer experience,
        UserLevel userLevel,
        long challengesCompleted,
        long challengesAccepted
) {
}
