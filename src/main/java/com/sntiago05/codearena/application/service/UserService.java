package com.sntiago05.codearena.application.service;

import com.sntiago05.codearena.application.exceptions.EmailAlreadyExistsException;
import com.sntiago05.codearena.application.exceptions.ResourceNotFoundException;
import com.sntiago05.codearena.application.ports.in.users.GetMyProfileUseCase;
import com.sntiago05.codearena.application.ports.in.users.GetUsersUseCase;
import com.sntiago05.codearena.application.ports.in.users.RegisterUserUseCase;
import com.sntiago05.codearena.application.ports.in.command.GetMyProfileCommand;
import com.sntiago05.codearena.application.ports.in.command.RegisterUserCommand;
import com.sntiago05.codearena.application.ports.in.command.getusers.GetUsersCommand;
import com.sntiago05.codearena.application.ports.in.result.GetMyProfileResult;
import com.sntiago05.codearena.application.ports.in.result.GetUsersResult;
import com.sntiago05.codearena.application.ports.in.result.RegisterUserResult;
import com.sntiago05.codearena.application.ports.out.ParticipationRepositoryPort;
import com.sntiago05.codearena.application.ports.out.PasswordHasherPort;
import com.sntiago05.codearena.application.ports.out.UserRepositoryPort;
import com.sntiago05.codearena.application.ports.out.data.UserPage;
import com.sntiago05.codearena.application.ports.out.data.UserQuery;
import com.sntiago05.codearena.application.service.mapper.userservicemapper.GetMyProfileMapper;
import com.sntiago05.codearena.application.service.mapper.userservicemapper.GetUsersMapper;
import com.sntiago05.codearena.application.service.mapper.userservicemapper.RegisterUserMapper;
import com.sntiago05.codearena.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Coordinates main user operations.
 */
@Service
@RequiredArgsConstructor
public class UserService implements RegisterUserUseCase, GetMyProfileUseCase, GetUsersUseCase {

    private final UserRepositoryPort userRepositoryPort;
    private final PasswordHasherPort passwordHasherPort;
    private final ParticipationRepositoryPort participationRepositoryPort;

    /**
     * @param command user registration data
     * @return registered user information
     * @throws EmailAlreadyExistsException if the email is already in use
     */
    @Override
    public RegisterUserResult registerUser(RegisterUserCommand command) {
        validateEmailNotTaken(command.email());
        String encodedPassword = passwordHasherPort.encode(command.password());
        User userToRegister = RegisterUserMapper.toUser(command, encodedPassword);
        return RegisterUserMapper.toRegisterUserResult(userRepositoryPort.save(userToRegister));
    }

    /**
     * @param command contains the user identifier
     * @return user profile data and participation statistics
     * @throws ResourceNotFoundException if the user does not exist
     */
    @Override
    public GetMyProfileResult getMyProfile(GetMyProfileCommand command) {
        User user = findUserByIdOrThrow(command.userId());
        long completedParticipations = countCompletedParticipations(command.userId());
        long acceptedParticipations = countAcceptedParticipations(command.userId());
        return GetMyProfileMapper.toGetMyProfileResult(user, completedParticipations, acceptedParticipations);
    }

    @Override
    public GetUsersResult getUsers(GetUsersCommand command) {
        UserQuery query = GetUsersMapper.toUserQuery(command);
        UserPage userPage = userRepositoryPort.findPage(query);
        return GetUsersMapper.toGetUsersResult(userPage);
    }

    private void validateEmailNotTaken(String email) {
        if (userRepositoryPort.existsByEmail(email)) {
            throw new EmailAlreadyExistsException("Email already exists");
        }
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
}
