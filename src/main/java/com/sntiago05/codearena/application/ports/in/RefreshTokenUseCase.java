package com.sntiago05.codearena.application.ports.in;

import com.sntiago05.codearena.application.ports.in.command.RefreshTokenCommand;
import com.sntiago05.codearena.application.ports.in.result.AuthenticationResult;

/**
 * Use case for refreshing authentication tokens.
 */
public interface RefreshTokenUseCase {
    AuthenticationResult refreshToken(RefreshTokenCommand command);
}
