package com.sntiago05.codearena.application.ports.out;

import com.sntiago05.codearena.application.ports.out.claims.AccessTokenClaims;
import com.sntiago05.codearena.application.ports.out.claims.RefreshTokenClaims;

/**
 * Port for parsing authentication tokens.
 */
public interface TokenParserPort {
    RefreshTokenClaims parseRefreshToken(String refreshToken);
    AccessTokenClaims parseAccessToken(String  accessToken);
}
