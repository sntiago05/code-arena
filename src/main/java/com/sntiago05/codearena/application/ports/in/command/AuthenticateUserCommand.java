package com.sntiago05.codearena.application.ports.in.command;

public record AuthenticateUserCommand(
        String email,
        String password
) {
}
