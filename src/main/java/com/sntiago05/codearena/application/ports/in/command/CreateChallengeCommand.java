package com.sntiago05.codearena.application.ports.in.command;

import com.sntiago05.codearena.domain.challenge.ChallengeDifficulty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Command to create a new challenge.
 */
public record CreateChallengeCommand(
        @NotBlank(message = "Title cannot be blank")
        String title,

        @NotBlank(message = "Description cannot be blank")
        String description,

        @NotNull(message = "Category ID cannot be null")
        UUID categoryId,

        @NotNull(message = "Difficulty cannot be null")
        ChallengeDifficulty difficulty,

        LocalDateTime startTime,
        LocalDateTime deadLine
) {
}
