package com.sntiago05.codearena.application.service;

import com.sntiago05.codearena.application.exceptions.InvalidTokenException;
import com.sntiago05.codearena.application.exceptions.ResourceNotFoundException;
import com.sntiago05.codearena.application.ports.in.RefreshTokenUseCase;
import com.sntiago05.codearena.application.ports.in.command.RefreshTokenCommand;
import com.sntiago05.codearena.application.ports.in.result.AuthenticationResult;
import com.sntiago05.codearena.application.ports.out.RefreshTokenRepositoryPort;
import com.sntiago05.codearena.application.ports.out.TokenGeneratorPort;
import com.sntiago05.codearena.application.ports.out.TokenParserPort;
import com.sntiago05.codearena.application.ports.out.UserRepositoryPort;
import com.sntiago05.codearena.application.ports.out.claims.RefreshTokenClaims;
import com.sntiago05.codearena.application.ports.out.data.AccessTokenData;
import com.sntiago05.codearena.application.ports.out.data.GeneratedRefreshToken;
import com.sntiago05.codearena.application.ports.out.data.RefreshTokenData;
import com.sntiago05.codearena.domain.refreshtoken.RefreshToken;
import com.sntiago05.codearena.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService implements RefreshTokenUseCase {

    private final RefreshTokenRepositoryPort refreshTokenRepositoryPort;
    private final TokenGeneratorPort tokenGeneratorPort;
    private final TokenParserPort tokenParserPort;
    private final UserRepositoryPort userRepositoryPort;

    @Override
    public AuthenticationResult refreshToken(RefreshTokenCommand command) {
        RefreshToken token = getAndValidateToken(command.token());
        User user = getUser(token.getUserId());
        
        token.revoke();
        
        GeneratedRefreshToken generatedRefreshToken = generateRefreshToken(user.getId());
        String accessToken = generateAccessToken(user.getId(), user);

        refreshTokenRepositoryPort.save(generatedRefreshToken.toDomain(user.getId()));
        
        return new AuthenticationResult(accessToken, generatedRefreshToken.token());
    }

    private RefreshToken getAndValidateToken(String tokenString) {
        RefreshTokenClaims refreshTokenClaims = tokenParserPort.parseRefreshToken(tokenString);
        RefreshToken token = refreshTokenRepositoryPort.findByToken(tokenString)
                .orElseThrow(() -> new ResourceNotFoundException("Refresh token not found"));
                
        if (!refreshTokenClaims.userId().equals(token.getUserId())) {
            throw new InvalidTokenException("Invalid token");
        }
        if (!token.isValid()) {
            throw new InvalidTokenException("Invalid or expired token");
        }
        return token;
    }

    private User getUser(UUID userId) {
        return userRepositoryPort.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    private GeneratedRefreshToken generateRefreshToken(UUID userId) {
        return tokenGeneratorPort.generateRefreshToken(new RefreshTokenData(userId));
    }

    private String generateAccessToken(UUID userId, User user) {
        return tokenGeneratorPort.generateAccessToken(new AccessTokenData(userId, user.getRole()));
    }
}
