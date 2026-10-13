package com.sntiago05.codearena.application.service.mapper.userservicemapper;

import com.sntiago05.codearena.application.ports.in.command.RegisterUserCommand;
import com.sntiago05.codearena.application.ports.in.result.RegisterUserResult;
import com.sntiago05.codearena.domain.user.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegisterUserMapperTest {

    @Test
    void shouldMapCommandToUser() {
        var command = new RegisterUserCommand("John Doe", "john@example.com", "plainPassword123");
        var encodedPassword = "encodedPassword123";

        User user = RegisterUserMapper.toUser(command, encodedPassword);

        assertNotNull(user);
        assertEquals("John Doe", user.getName());
        assertEquals("john@example.com", user.getEmail());
        assertEquals(encodedPassword, user.getPassword());
    }

    @Test
    void shouldMapUserToRegisterUserResult() {
        var user = User.createPlayer("John Doe", "john@example.com", "encodedPassword123");

        RegisterUserResult result = RegisterUserMapper.toRegisterUserResult(user);

        assertNotNull(result);
        assertEquals("John Doe", result.name());
        assertEquals("john@example.com", result.email());
        assertEquals(user.getAccumulatedExperience(), result.experience());
    }
}
