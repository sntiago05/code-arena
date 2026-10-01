package com.sntiago05.codearena.application.ports.in.result;

public record RegisterUserResult(
        String name,
        String email,
        Integer experience
) {
}
