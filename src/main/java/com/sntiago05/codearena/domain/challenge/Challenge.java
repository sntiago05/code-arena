package com.sntiago05.codearena.domain.challenge;

import com.sntiago05.codearena.domain.exceptions.EmptyAttributeException;
import com.sntiago05.codearena.domain.exceptions.InvalidTimeRangeException;
import com.sntiago05.codearena.domain.exceptions.NullAttributeException;
import com.sntiago05.codearena.domain.exceptions.NegativeAttributeException;

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
        if (id == null) {
            throw new NullAttributeException("id");
        }
        this.id = id;
    }

    private void setTitle(String title) {
        if (title == null) {
            throw new NullAttributeException("title");
        }
        if (title.trim().isEmpty()) {
            throw new EmptyAttributeException("title");
        }
        this.title = title;
    }

    private void setDescription(String description) {
        if (description == null) {
            throw new NullAttributeException("description");
        }
        if (description.trim().isEmpty()) {
            throw new EmptyAttributeException("description");
        }
        this.description = description;
    }

    private void setCategory(ChallengeCategory category) {
        if (category == null) {
            throw new NullAttributeException("category");
        }
        this.category = category;
    }

    private void setDifficulty(ChallengeDifuculty difficulty) {
        if (difficulty == null) {
            throw new NullAttributeException("difuculty");
        }
        this.difficulty = difficulty;
        setExperience(ChallengeExperienceCalculator.calculate(difficulty));
    }

    private void setCreatedAt(LocalDateTime createdAt) {
        if (createdAt == null) {
            throw new NullAttributeException("startTime");
        }
        this.createdAt = createdAt;
        if (this.deadLine != null && this.createdAt.isAfter(this.deadLine)) {
            throw new InvalidTimeRangeException("startTime cannot be after endTime");
        }
    }

    private void setDeadLine(LocalDateTime deadLine) {
        if (deadLine == null) {
            throw new NullAttributeException("endTime");
        }
        this.deadLine = deadLine;
        if (this.createdAt != null && this.deadLine.isBefore(this.createdAt)) {
            throw new InvalidTimeRangeException("endTime cannot be before startTime");
        }
    }

    private void setState(ChallengeState state) {
        if (state == null) {
            throw new NullAttributeException("state");
        }
        this.state = state;
    }

    private void setExperience(Integer experience) {
        if (experience == null) {
            throw new NullAttributeException("experience");
        }
        if (experience < 0) {
            throw new NegativeAttributeException("experience");
        }
        this.experience = experience;
    }
}
