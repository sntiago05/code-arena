package com.sntiago05.codearena.application.ports.in.result;

import com.sntiago05.codearena.domain.challenge.ChallengeDifficulty;
import com.sntiago05.codearena.domain.challenge.ChallengeState;

import java.util.UUID;

public record ChallengeCreated(
        UUID id,
        String title,
        String description,
        ChallengeDifficulty difficulty,
        ChallengeState state
) {
}
