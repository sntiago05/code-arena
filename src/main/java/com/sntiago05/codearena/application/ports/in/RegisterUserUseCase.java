package com.sntiago05.codearena.application.ports.in;

import com.sntiago05.codearena.application.ports.in.command.RegisterUserCommand;
import com.sntiago05.codearena.application.ports.in.result.RegisterUserResult;
import com.sntiago05.codearena.domain.user.User;

/**
 * Use case for registering a new user.
 */
public interface RegisterUserUseCase {

    public RegisterUserResult registerUser(RegisterUserCommand command);
}
