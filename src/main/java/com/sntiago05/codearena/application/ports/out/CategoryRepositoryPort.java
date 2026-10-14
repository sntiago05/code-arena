package com.sntiago05.codearena.application.ports.out;


import com.sntiago05.codearena.domain.challenge.ChallengeCategory;

import java.util.Optional;
import java.util.UUID;

public interface CategoryRepositoryPort {
    Optional<ChallengeCategory> findBydId(UUID id);
}
