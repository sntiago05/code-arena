package com.sntiago05.codearena.application.ports.out;

import com.sntiago05.codearena.domain.refreshtoken.RefreshToken;

public interface RefreshTokenRepositoryPort {
    RefreshToken save(RefreshToken refreshToken);
}
