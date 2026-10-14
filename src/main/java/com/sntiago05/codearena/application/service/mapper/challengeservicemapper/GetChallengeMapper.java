package com.sntiago05.codearena.application.service.mapper.challengeservicemapper;

import com.sntiago05.codearena.application.ports.in.result.GetChallengeResult;
import com.sntiago05.codearena.domain.challenge.Challenge;

public class GetChallengeMapper {

    private GetChallengeMapper() {
    }

    public static GetChallengeResult toResult(Challenge challenge) {
        return new GetChallengeResult(challenge.getTitle(), challenge.getDescription(), challenge.getCategory().getId(), challenge.getDifficulty(), challenge.getCreatedAt(), challenge.getStartTime(), challenge.getDeadLine(), challenge.getState(), challenge.getExperience());
    }
}
