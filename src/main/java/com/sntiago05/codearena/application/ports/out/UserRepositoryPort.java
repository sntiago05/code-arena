package com.sntiago05.codearena.application.ports.out;

import com.sntiago05.codearena.domain.user.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepositoryPort {
    Optional<User> findByEmail(String email);

    Optional<User> findById(UUID id);
    boolean existsByEmail(String email);
    User save(User user);
}
