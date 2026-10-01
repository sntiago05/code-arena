package com.sntiago05.codearena.application.ports.out;

import java.util.UUID;

public interface ParticipationRepositoryPort {
    long countAcceptedByUser(UUID uuid);
    long countCompletedByUser(UUID uuid);
}
