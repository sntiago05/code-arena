package com.sntiago05.codearena.domain.achievement.conditions.implementations;

import com.sntiago05.codearena.domain.achievement.AchievementContext;
import com.sntiago05.codearena.domain.achievement.conditions.AchievementCondition;
import com.sntiago05.codearena.domain.participation.ParticipationState;
import com.sntiago05.codearena.domain.utils.ValidationUtils;

import java.util.UUID;

/** Condition based on a required number of completed challenges. */
public class CompletedChallengesConditions implements AchievementCondition {
    private Integer value;

    public CompletedChallengesConditions(Integer value) {
        setValue(value);
    }

    @Override
    public boolean isSatisfiedBy(AchievementContext achievementContext) {
        UUID userId = achievementContext.getUser().getId();
        long completedCount = achievementContext.getParticipations().stream()
                .filter(p -> p.getUserId().equals(userId) && p.getState().equals(ParticipationState.APPROVED))
                .count();
        
        return completedCount >= value;
    }

    private void setValue(Integer value) {
        ValidationUtils.requireNonNull(value, "value");
        ValidationUtils.requireNonNegative(value, "value");
        this.value = value;
    }
}
