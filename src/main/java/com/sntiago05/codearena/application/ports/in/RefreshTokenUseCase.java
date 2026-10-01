package com.sntiago05.codearena.application.ports.in;

import com.sntiago05.codearena.application.ports.in.command.RefreshTokenCommand;
import com.sntiago05.codearena.application.ports.in.result.AuthenticationResult;

public interface RefreshTokenUseCase {
    AuthenticationResult refreshToken(RefreshTokenCommand command);
}
