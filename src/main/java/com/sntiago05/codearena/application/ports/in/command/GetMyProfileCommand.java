package com.sntiago05.codearena.application.ports.in.command;

import java.util.UUID;

/**
 * Command to retrieve a user's profile.
 */
public record GetMyProfileCommand(
        UUID userId
) {
}
