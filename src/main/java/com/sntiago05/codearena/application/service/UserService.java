package com.sntiago05.codearena.application.service;

import com.sntiago05.codearena.application.ports.in.RegisterUserUseCase;
import com.sntiago05.codearena.application.ports.in.command.RegisterUserCommand;
import com.sntiago05.codearena.application.ports.in.result.RegisterUserResult;
import com.sntiago05.codearena.application.ports.out.PasswordHasherPort;
import com.sntiago05.codearena.application.ports.out.UserRepositoryPort;
import com.sntiago05.codearena.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements RegisterUserUseCase {

    private final UserRepositoryPort userRepositoryPort;
    private final PasswordHasherPort passwordHasherPort;

    @Override
    public RegisterUserResult registerUser(RegisterUserCommand command) {
        if (userRepositoryPort.existsByEmail(command.email())) {
            throw new RuntimeException("Email already exists");
        }
        User toRegister = User.createPlayer(
                command.name(),
                command.email(),
                passwordHasherPort.encode(command.password())
        );

        User registered = userRepositoryPort.save(toRegister);
        return new RegisterUserResult(registered.getName(), registered.getEmail(), registered.getAccumulatedExperience());
    }
}
