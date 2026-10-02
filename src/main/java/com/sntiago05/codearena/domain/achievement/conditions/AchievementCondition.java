package com.sntiago05.codearena.domain.achievement.conditions;

import com.sntiago05.codearena.domain.achievement.AchievementContext;

/** Represents a condition that must be met to unlock an achievement. */
public interface AchievementCondition {
    boolean isSatisfiedBy(AchievementContext achievementContext);
}
