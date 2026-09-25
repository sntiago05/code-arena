package com.sntiago05.codearena.domain.achievement.conditions.implementations;

import com.sntiago05.codearena.domain.achievement.AchievementContext;
import com.sntiago05.codearena.domain.achievement.conditions.AchievementCondition;
import com.sntiago05.codearena.domain.challenge.ChallengeDifuculty;
import com.sntiago05.codearena.domain.participation.ParticipationState;
import com.sntiago05.codearena.domain.utils.ValidationUtils;

import java.util.UUID;

public class CompletedChallengesByDifficultyCondition implements AchievementCondition {

    private ChallengeDifuculty value;

    public CompletedChallengesByDifficultyCondition(ChallengeDifuculty value) {
        setValue(value);
    }

    @Override
    public boolean isSatisfiedBy(AchievementContext achievementContext) {
        UUID userId = achievementContext.getUser().getId();
        return achievementContext.getParticipations().stream().filter(p -> p.getUserId().equals(userId)).anyMatch(participation -> participation.getChallenge().getDifficulty().equals(value) && participation.getState().equals(ParticipationState.APPROVED));
    }

    private void setValue(ChallengeDifuculty value) {
        ValidationUtils.requireNonNull(value, "Value must not be null");
        this.value = value;
    }
}
