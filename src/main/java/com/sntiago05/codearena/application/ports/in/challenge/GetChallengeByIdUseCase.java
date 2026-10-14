package com.sntiago05.codearena.application.ports.in.challenge;

import com.sntiago05.codearena.application.ports.in.command.challenges.GetChallengeByIdCommand;
import com.sntiago05.codearena.application.ports.in.result.GetChallengeResult;

import java.util.UUID;

public interface GetChallengeByIdUseCase {

    GetChallengeResult findById(GetChallengeByIdCommand command);
}
