package com.sntiago05.codearena.application.ports.in.command;

import com.sntiago05.codearena.domain.challenge.ChallengeDifficulty;

import java.util.UUID;

public record CreateChallengeCommand(
        String title,
        String description,
         UUID categoryId,
        ChallengeDifficulty difficulty
) {
}
