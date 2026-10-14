package com.sntiago05.codearena.application.ports.out;

import com.sntiago05.codearena.domain.challenge.Challenge;

public interface ChallengeRepositoryPort {
    Challenge save(Challenge challenge);
    boolean existsByTitle(String title);
}
