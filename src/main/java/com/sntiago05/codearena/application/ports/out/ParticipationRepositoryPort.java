package com.sntiago05.codearena.application.ports.out;

import java.util.UUID;

/**
 * Port for tracking user participation metrics.
 */
public interface ParticipationRepositoryPort {
    long countAcceptedByUser(UUID uuid);
    long countCompletedByUser(UUID uuid);
}
