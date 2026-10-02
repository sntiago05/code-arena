package com.sntiago05.codearena.application.ports.in;

import com.sntiago05.codearena.application.ports.in.command.AuthenticateUserCommand;
import com.sntiago05.codearena.application.ports.in.result.AuthenticationResult;

/**
 * Use case for authenticating users.
 */
public interface AuthenticateUserUseCase {

    AuthenticationResult authenticate(AuthenticateUserCommand command);

}
