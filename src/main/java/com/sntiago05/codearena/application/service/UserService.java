package com.sntiago05.codearena.application.service;

import com.sntiago05.codearena.application.exceptions.EmailAlreadyExistsException;
import com.sntiago05.codearena.application.exceptions.ResourceNotFoundException;
import com.sntiago05.codearena.application.ports.in.GetMyProfileUseCase;
import com.sntiago05.codearena.application.ports.in.RegisterUserUseCase;
import com.sntiago05.codearena.application.ports.in.command.GetMyProfileCommand;
import com.sntiago05.codearena.application.ports.in.command.RegisterUserCommand;
import com.sntiago05.codearena.application.ports.in.result.GetMyProfileResult;
import com.sntiago05.codearena.application.ports.in.result.RegisterUserResult;
import com.sntiago05.codearena.application.ports.out.ParticipationRepositoryPort;
import com.sntiago05.codearena.application.ports.out.PasswordHasherPort;
import com.sntiago05.codearena.application.ports.out.UserRepositoryPort;
import com.sntiago05.codearena.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService implements RegisterUserUseCase, GetMyProfileUseCase {

    private final UserRepositoryPort userRepositoryPort;
    private final PasswordHasherPort passwordHasherPort;
    private final ParticipationRepositoryPort participationRepositoryPort;

    @Override
    public RegisterUserResult registerUser(RegisterUserCommand command) {
        validateEmailNotTaken(command.email());
        return mapToResult(userRepositoryPort.save(buildUserToRegister(command)));
    }

    @Override
    public GetMyProfileResult getMyProfile(GetMyProfileCommand command) {
        User user = findUserByIdOrThrow(command.userId());
        long completedParticipations = countCompletedParticipations(command.userId());
        long acceptedParticipations = countAcceptedParticipations(command.userId());
        return mapToProfileResult(user, completedParticipations, acceptedParticipations);
    }

    private void validateEmailNotTaken(String email) {
        if (userRepositoryPort.existsByEmail(email)) {
            throw new EmailAlreadyExistsException("Email already exists");
        }
    }

    private User buildUserToRegister(RegisterUserCommand command) {
        return User.createPlayer(
                command.name(),
                command.email(),
                passwordHasherPort.encode(command.password())
        );
    }

    private RegisterUserResult mapToResult(User registered) {
        return new RegisterUserResult(
                registered.getName(),
                registered.getEmail(),
                registered.getAccumulatedExperience()
        );
    }

    private User findUserByIdOrThrow(UUID userId) {
        return userRepositoryPort.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    private long countCompletedParticipations(UUID userId) {
        return participationRepositoryPort.countCompletedByUser(userId);
    }

    private long countAcceptedParticipations(UUID userId) {
        return participationRepositoryPort.countAcceptedByUser(userId);
    }

    private GetMyProfileResult mapToProfileResult(User user, long completedParticipations, long acceptedParticipations) {
        return new GetMyProfileResult(
                user.getName(),
                user.getEmail(),
                user.getAccumulatedExperience(),
                user.getLevel(),
                completedParticipations,
                acceptedParticipations
        );
    }
}
