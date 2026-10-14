package com.sntiago05.codearena.application.ports.in.result;

import com.sntiago05.codearena.domain.challenge.ChallengeDifficulty;
import com.sntiago05.codearena.domain.challenge.ChallengeState;

import java.time.LocalDateTime;
import java.util.UUID;

public record GetChallengeResult(
        String title,
        String description,
        UUID categoryId,
        ChallengeDifficulty difficulty,
        LocalDateTime createdAt,
        LocalDateTime startTime,
        LocalDateTime deadLine,
        ChallengeState state,
        Integer experience
) {
}
