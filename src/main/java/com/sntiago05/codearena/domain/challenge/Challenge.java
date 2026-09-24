package com.sntiago05.codearena.domain.challenge;

import com.sntiago05.codearena.domain.utils.ValidationUtils;

import java.time.LocalDateTime;
import java.util.UUID;

public class Challenge {
    private UUID id;
    private String title;
    private String description;
    private ChallengeCategory category;
    private ChallengeDifuculty difficulty;
    private LocalDateTime createdAt;
    private LocalDateTime deadLine;
    private ChallengeState state;
    private Integer experience;

    public Challenge(UUID id, String title, String description, ChallengeCategory category, 
                     ChallengeDifuculty difuculty, LocalDateTime startTime, LocalDateTime endTime, 
                     ChallengeState state) {
        setId(id);
        setTitle(title);
        setDescription(description);
        setCategory(category);
        setDifficulty(difuculty);
        setCreatedAt(startTime);
        setDeadLine(endTime);
        setState(state);
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

    private void setCategory(ChallengeCategory category) {
        ValidationUtils.requireNonNull(category, "category");
        this.category = category;
    }

    private void setDifficulty(ChallengeDifuculty difficulty) {
        ValidationUtils.requireNonNull(difficulty, "difuculty");
        this.difficulty = difficulty;
        setExperience(ChallengeExperienceCalculator.calculate(difficulty));
    }

    private void setCreatedAt(LocalDateTime createdAt) {
        ValidationUtils.requireNonNull(createdAt, "created at");
        this.createdAt = createdAt;
        ValidationUtils.validateTimeOrder(this.createdAt, this.deadLine, "created at cannot be after endTime");
    }

    private void setDeadLine(LocalDateTime deadLine) {
        ValidationUtils.requireNonNull(deadLine, "dead line");
        this.deadLine = deadLine;
        ValidationUtils.validateTimeOrder(this.createdAt, this.deadLine, "deadline cannot be before startTime");
    }

    private void setState(ChallengeState state) {
        ValidationUtils.requireNonNull(state, "state");
        this.state = state;
    }

    private void setExperience(Integer experience) {
        ValidationUtils.requireNonNegative(experience, "experience");
        this.experience = experience;
    }
}
