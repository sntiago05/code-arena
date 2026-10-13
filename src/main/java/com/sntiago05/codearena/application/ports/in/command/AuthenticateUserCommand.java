package com.sntiago05.codearena.application.ports.in.command;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * Command containing credentials for user authentication.
 */
public record AuthenticateUserCommand(
        @NotBlank(message = "Email cannot be blank")
        @Email(message = "Invalid email format")
        String email,

        @NotBlank(message = "Password cannot be blank")
        String password
) {
}
