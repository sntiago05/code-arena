package com.sntiago05.codearena.application.ports.in.command;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

/**
 * Command to retrieve a user's profile.
 */
public record GetMyProfileCommand(
        @NotNull(message = "User ID cannot be null")
        UUID userId
) {
}
