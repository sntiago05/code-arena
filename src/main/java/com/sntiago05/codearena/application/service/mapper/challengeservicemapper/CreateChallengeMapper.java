package com.sntiago05.codearena.application.service.mapper.challengeservicemapper;

import com.sntiago05.codearena.application.ports.in.command.CreateChallengeCommand;
import com.sntiago05.codearena.application.ports.in.result.ChallengeCreated;
import com.sntiago05.codearena.domain.challenge.Challenge;
import com.sntiago05.codearena.domain.challenge.ChallengeCategory;

public final class CreateChallengeMapper {

    private CreateChallengeMapper() {}

    public static Challenge toChallenge(CreateChallengeCommand command, ChallengeCategory category) {
        return new Challenge(
                command.title(),
                command.description(),
                category,
                command.difficulty(),
                command.startTime(),
                command.deadLine()
        );
    }

    public static ChallengeCreated toChallengeCreated(Challenge challenge) {
        return new ChallengeCreated(
                challenge.getId(),
                challenge.getTitle(),
                challenge.getDescription(),
                challenge.getDifficulty(),
                challenge.getState()
        );
    }

    public static ChallengeCreated toResult(Challenge challenge) {
        return toChallengeCreated(challenge);
    }
}
