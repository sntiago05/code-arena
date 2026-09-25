package com.sntiago05.codearena.domain.achievement.conditions.implementations;

import com.sntiago05.codearena.domain.achievement.AchievementContext;
import com.sntiago05.codearena.domain.achievement.conditions.AchievementCondition;
import com.sntiago05.codearena.domain.utils.ValidationUtils;

public class ExperienceCondition implements AchievementCondition {
    private Integer value;

    public ExperienceCondition(Integer value) {
        setValue(value);
    }

    @Override
    public boolean isSatisfiedBy(AchievementContext achievementContext) {
        return achievementContext.getUser().getAccumulatedExperience() >= value;
    }

    private void setValue(Integer value) {
        ValidationUtils.requireNonNull(value, "Value");
        ValidationUtils.requireNonNegative(value, "Value");
        this.value = value;
    }
}
