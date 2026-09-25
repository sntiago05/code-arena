package com.sntiago05.codearena.domain.achievement.conditions;

import com.sntiago05.codearena.domain.achievement.AchievementContext;

public interface AchievementCondition {
    boolean isSatisfiedBy(AchievementContext achievementContext);
}
