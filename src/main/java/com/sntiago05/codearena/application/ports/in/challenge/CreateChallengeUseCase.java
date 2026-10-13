package com.sntiago05.codearena.application.ports.in.challenge;

import com.sntiago05.codearena.application.ports.in.command.CreateChallengeCommand;
import com.sntiago05.codearena.application.ports.in.result.ChallengeCreated;

public interface CreateChallengeUseCase {
    ChallengeCreated create(CreateChallengeCommand command);
}
