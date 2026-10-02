package com.sntiago05.codearena.domain.achievement.conditions;

import com.sntiago05.codearena.domain.achievement.AchievementsConditionsCatalog;

/** Definition of an achievement condition including its type and required value. */
public record AchievementConditionDefinition(AchievementsConditionsCatalog type, Object value) {
}
