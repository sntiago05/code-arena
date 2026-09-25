package com.sntiago05.codearena.domain.achievement;

import com.sntiago05.codearena.domain.achievement.conditions.AchievementCondition;
import com.sntiago05.codearena.domain.exceptions.EmptyAttributeException;
import com.sntiago05.codearena.domain.exceptions.NullAttributeException;
import lombok.Getter;

import java.util.UUID;

@Getter
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
        if (id == null)
            throw new NullAttributeException("id");

        this.id = id;
    }

    private void setTitle(String title) {
        if (title == null)
            throw new NullAttributeException("title");

        if (title.trim().isEmpty())
            throw new EmptyAttributeException("title");

        this.title = title;
    }

    private void setDescription(String description) {
        if (description == null)
            throw new NullAttributeException("description");

        if (description.trim().isEmpty())
            throw new EmptyAttributeException("description");

        this.description = description;
    }

    private void setCondition(AchievementCondition condition) {
        if (condition == null)
            throw new NullAttributeException("condition");

        this.condition = condition;
    }

    public boolean isCompleted(AchievementContext achievementContext) {
        if (achievementContext == null)
            throw new NullAttributeException("achievementContext");

        return condition.isSatisfiedBy(achievementContext);
    }
}
