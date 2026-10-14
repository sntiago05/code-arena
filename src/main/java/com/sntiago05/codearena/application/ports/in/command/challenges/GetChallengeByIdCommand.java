package com.sntiago05.codearena.application.ports.in.command.challenges;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record GetChallengeByIdCommand(
        @NotNull(message = "challenge ID cannot be null")
        UUID uuid
) {
}
