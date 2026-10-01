package com.sntiago05.codearena.application.ports.out;

import com.sntiago05.codearena.application.ports.out.claims.AccessTokenClaims;
import com.sntiago05.codearena.application.ports.out.claims.RefreshTokenClaims;

public interface TokenParserPort {
    RefreshTokenClaims parseRefreshToken(String refreshToken);
    AccessTokenClaims parseAccessToken(String  accessToken);
}
