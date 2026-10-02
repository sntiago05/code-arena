package com.sntiago05.codearena.application.service;

import com.sntiago05.codearena.application.exceptions.InvalidCredentialsException;
import com.sntiago05.codearena.application.exceptions.ResourceNotFoundException;
import com.sntiago05.codearena.application.exceptions.UserInactiveException;
import com.sntiago05.codearena.application.ports.in.AuthenticateUserUseCase;
import com.sntiago05.codearena.application.ports.in.command.AuthenticateUserCommand;
import com.sntiago05.codearena.application.ports.in.result.AuthenticationResult;
import com.sntiago05.codearena.application.ports.out.PasswordHasherPort;
import com.sntiago05.codearena.application.ports.out.RefreshTokenRepositoryPort;
import com.sntiago05.codearena.application.ports.out.TokenGeneratorPort;
import com.sntiago05.codearena.application.ports.out.UserRepositoryPort;
import com.sntiago05.codearena.application.ports.out.data.AccessTokenData;
import com.sntiago05.codearena.application.ports.out.data.GeneratedRefreshToken;
import com.sntiago05.codearena.application.ports.out.data.RefreshTokenData;
import com.sntiago05.codearena.domain.user.User;
import com.sntiago05.codearena.domain.user.UserState;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Service for authenticating users and issuing access tokens.
 */
@Service
@RequiredArgsConstructor
public class AuthenticateUserService implements AuthenticateUserUseCase {

    private final UserRepositoryPort userRepository;
    private final TokenGeneratorPort tokenGenerator;
    private final PasswordHasherPort passwordHasher;
    private final RefreshTokenRepositoryPort refreshTokenRepository;


    @Override
    public AuthenticationResult authenticate(AuthenticateUserCommand command) {

        User user = getUser(command);
        if (user.getState().equals(UserState.INACTIVE)) {
            throw new UserInactiveException("User account is inactive");
        }
        UUID userId = user.getId();
        GeneratedRefreshToken generatedRefreshToken = generateRefreshToken(userId);
        String accessToken = generateAccessToken(userId, user);
        
        refreshTokenRepository.save(generatedRefreshToken.toDomain(userId));
        
        return new AuthenticationResult(
                accessToken,
                generatedRefreshToken.token()
        );
    }

    private String generateAccessToken(UUID userId, User user) {
        return tokenGenerator.generateAccessToken(new AccessTokenData(userId, user.getRole()));
    }

    private GeneratedRefreshToken generateRefreshToken(UUID userId) {
        return tokenGenerator.generateRefreshToken(new RefreshTokenData(userId));
    }

    private User getUser(AuthenticateUserCommand command) {
        User user = userRepository.findByEmail(command.email())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (!passwordHasher.matches(command.password(), user.getPassword()))
            throw new InvalidCredentialsException("Invalid password");

        return user;
    }
}
