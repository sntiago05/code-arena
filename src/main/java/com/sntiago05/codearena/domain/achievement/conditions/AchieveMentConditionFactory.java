package com.sntiago05.codearena.domain.achievement.conditions;

import com.sntiago05.codearena.domain.achievement.AchievementsConditionsCatalog;
import com.sntiago05.codearena.domain.achievement.conditions.implementations.CompletedChallengesByDifficultyCondition;
import com.sntiago05.codearena.domain.achievement.conditions.implementations.CompletedChallengesConditions;
import com.sntiago05.codearena.domain.achievement.conditions.implementations.ExperienceCondition;
import com.sntiago05.codearena.domain.challenge.ChallengeDifficulty;
import com.sntiago05.codearena.domain.utils.ValidationUtils;

/** Factory for creating achievement conditions. */
public class AchieveMentConditionFactory {
    private AchieveMentConditionFactory() {
    }

    public static AchievementCondition createCondition(AchievementsConditionsCatalog type, Object value) {
        switch (type) {
            case COMPLETED_CHALLENGES -> {
                Integer intValue = ValidationUtils.requireInteger(value, "value");
                return new CompletedChallengesConditions(intValue);
            }
            case EXPERIENCE -> {
                Integer expValue = ValidationUtils.requireInteger(value, "value");
                return new ExperienceCondition(expValue);
            }
            case COMPLETED_DIFFICULTY -> {
                ChallengeDifficulty difficulty = ValidationUtils.requireEnumType(value, ChallengeDifficulty.class, "value");
                return new CompletedChallengesByDifficultyCondition(difficulty);
            }
            default -> throw new IllegalArgumentException("Unknown condition type: " + type);
        }
    }
}
