package com.sntiago05.codearena.application.ports.in.result;

import com.sntiago05.codearena.domain.user.UserLevel;

/**
 * Result object containing a user's profile details.
 */
public record GetMyProfileResult(
        String name,
        String email,
        Integer experience,
        UserLevel userLevel,
        long challengesCompleted,
        long challengesAccepted
) {
}
