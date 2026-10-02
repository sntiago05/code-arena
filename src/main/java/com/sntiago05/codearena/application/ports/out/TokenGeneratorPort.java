package com.sntiago05.codearena.application.ports.out;

import com.sntiago05.codearena.application.ports.out.data.AccessTokenData;
import com.sntiago05.codearena.application.ports.out.data.GeneratedRefreshToken;
import com.sntiago05.codearena.application.ports.out.data.RefreshTokenData;

/**
 * Port for generating authentication tokens.
 */
public interface TokenGeneratorPort {
    String generateAccessToken(AccessTokenData accessTokenData);

    GeneratedRefreshToken generateRefreshToken(RefreshTokenData refreshTokenData);
}
