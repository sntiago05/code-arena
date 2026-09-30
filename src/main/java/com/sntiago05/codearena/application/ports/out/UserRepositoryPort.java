package com.sntiago05.codearena.application.ports.out;

import com.sntiago05.codearena.domain.user.User;

import java.util.Optional;

public interface UserRepositoryPort {
    Optional<User> findByEmail(String email);
}
