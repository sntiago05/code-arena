package com.sntiago05.codearena.application.ports.in.command;

import com.sntiago05.codearena.application.ports.in.command.getusers.GetUsersCommand;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CommandValidationTest {

    private static Validator validator;

    @BeforeAll
    static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void refreshTokenCommandValidation() {
        var valid = new RefreshTokenCommand("valid-token");
        assertTrue(validator.validate(valid).isEmpty());

        var blank = new RefreshTokenCommand("   ");
        assertFalse(validator.validate(blank).isEmpty());

        var nullToken = new RefreshTokenCommand(null);
        assertFalse(validator.validate(nullToken).isEmpty());
    }

    @Test
    void authenticateUserCommandValidation() {
        var valid = new AuthenticateUserCommand("user@example.com", "password123");
        assertTrue(validator.validate(valid).isEmpty());

        var invalidEmail = new AuthenticateUserCommand("not-an-email", "password123");
        assertFalse(validator.validate(invalidEmail).isEmpty());

        var blankPassword = new AuthenticateUserCommand("user@example.com", "  ");
        assertFalse(validator.validate(blankPassword).isEmpty());
    }

    @Test
    void getMyProfileCommandValidation() {
        var valid = new GetMyProfileCommand(UUID.randomUUID());
        assertTrue(validator.validate(valid).isEmpty());

        var nullId = new GetMyProfileCommand(null);
        assertFalse(validator.validate(nullId).isEmpty());
    }

    @Test
    void registerUserCommandValidation() {
        var valid = new RegisterUserCommand("Alice", "alice@example.com", "pass123");
        assertTrue(validator.validate(valid).isEmpty());

        var blankName = new RegisterUserCommand("  ", "alice@example.com", "pass123");
        assertFalse(validator.validate(blankName).isEmpty());

        var invalidEmail = new RegisterUserCommand("Alice", "not-email", "pass123");
        assertFalse(validator.validate(invalidEmail).isEmpty());

        var blankPass = new RegisterUserCommand("Alice", "alice@example.com", "");
        assertFalse(validator.validate(blankPass).isEmpty());
    }

    @Test
    void getUsersCommandValidation() {
        var valid = new GetUsersCommand(0, 10, null, null);
        assertTrue(validator.validate(valid).isEmpty());

        var negativePage = new GetUsersCommand(-1, 10, null, null);
        assertFalse(validator.validate(negativePage).isEmpty());

        var zeroSize = new GetUsersCommand(0, 0, null, null);
        assertFalse(validator.validate(zeroSize).isEmpty());

        var nullValues = new GetUsersCommand(null, null, null, null);
        assertFalse(validator.validate(nullValues).isEmpty());
    }
}
