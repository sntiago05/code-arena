package com.sntiago05.codearena.application.service.mapper.userservicemapper;

import com.sntiago05.codearena.application.ports.in.command.RegisterUserCommand;
import com.sntiago05.codearena.application.ports.in.result.RegisterUserResult;
import com.sntiago05.codearena.domain.user.User;

public final class RegisterUserMapper {

    private RegisterUserMapper() {}

    public static User toUser(RegisterUserCommand command, String encodedPassword) {
        return User.createPlayer(
                command.name(),
                command.email(),
                encodedPassword
        );
    }

    public static RegisterUserResult toRegisterUserResult(User registered) {
        return new RegisterUserResult(
                registered.getName(),
                registered.getEmail(),
                registered.getAccumulatedExperience()
        );
    }

    public static RegisterUserResult toResult(User registered) {
        return toRegisterUserResult(registered);
    }
}
