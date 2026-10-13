package com.sntiago05.codearena.application.ports.in.users;

import com.sntiago05.codearena.application.ports.in.command.RegisterUserCommand;
import com.sntiago05.codearena.application.ports.in.result.RegisterUserResult;

/**
 * Use case for registering a new user.
 */
public interface RegisterUserUseCase {

    public RegisterUserResult registerUser(RegisterUserCommand command);
}
