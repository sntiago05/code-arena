package com.sntiago05.codearena.domain.achievement;

import com.sntiago05.codearena.domain.achievement.conditions.AchievementCondition;
import com.sntiago05.codearena.domain.utils.ValidationUtils;
import lombok.Getter;

import java.util.UUID;

@Getter
/** Represents an achievement that a user can earn by satisfying specific conditions. */
public class Achievement {
    private UUID id;
    private String title;
    private String description;
    private AchievementCondition condition;

    public Achievement(UUID id, String title, String description, AchievementCondition condition) {
        setId(id);
        setTitle(title);
        setDescription(description);
        setCondition(condition);
    }

    private void setId(UUID id) {
        ValidationUtils.requireNonNull(id, "id");
        this.id = id;
    }

    private void setTitle(String title) {
        ValidationUtils.requireNonEmpty(title, "title");
        this.title = title;
    }

    private void setDescription(String description) {
        ValidationUtils.requireNonEmpty(description, "description");
        this.description = description;
    }

    private void setCondition(AchievementCondition condition) {
        ValidationUtils.requireNonNull(condition, "condition");
        this.condition = condition;
    }

    public boolean isCompleted(AchievementContext achievementContext) {
        ValidationUtils.requireNonNull(achievementContext, "achievementContext");
        return condition.isSatisfiedBy(achievementContext);
    }
}
