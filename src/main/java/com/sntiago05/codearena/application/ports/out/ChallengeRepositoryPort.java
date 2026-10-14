package com.sntiago05.codearena.application.ports.out;

import com.sntiago05.codearena.domain.challenge.Challenge;

import java.util.Optional;
import java.util.UUID;

public interface ChallengeRepositoryPort {
    Challenge save(Challenge challenge);
    boolean existsByTitle(String title);
    Optional<Challenge> findById(UUID uuid);
}
