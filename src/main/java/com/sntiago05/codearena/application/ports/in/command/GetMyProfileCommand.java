package com.sntiago05.codearena.application.ports.in.command;

import java.util.UUID;

public record GetMyProfileCommand(
        UUID userId
) {
}
