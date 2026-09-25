package com.sntiago05.codearena.domain.achievement.conditions.implementations;

import com.sntiago05.codearena.domain.achievement.AchievementContext;
import com.sntiago05.codearena.domain.achievement.conditions.AchievementCondition;
import com.sntiago05.codearena.domain.participation.ParticipationState;
import com.sntiago05.codearena.domain.utils.ValidationUtils;

import java.util.UUID;

public class CompletedChallengesConditions implements AchievementCondition {
    private Integer value;

    public CompletedChallengesConditions(Integer value) {
        settValue(value);
    }

    @Override
    public boolean isSatisfiedBy(AchievementContext achievementContext) {
        UUID userId = achievementContext.getUser().getId();
        return achievementContext.getParticipations().stream().filter(
                p -> p.getUserId().equals(userId) && p.getState().equals(ParticipationState.APPROVED)
        ).count() >= value;
    }

    private void settValue(Integer value) {
        ValidationUtils.requireNonNull(value, "value");
        ValidationUtils.requireNonNegative(value, "value");
        this.value = value;
    }
}
