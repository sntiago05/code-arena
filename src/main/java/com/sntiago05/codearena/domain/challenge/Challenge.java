package com.sntiago05.codearena.domain.challenge;

import com.sntiago05.codearena.domain.exceptions.InvalidAttributeException;
import com.sntiago05.codearena.domain.utils.ValidationUtils;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Represents a coding challenge.
 */
@Getter
public class Challenge {
    private UUID id;
    private String title;
    private String description;
    private ChallengeCategory category;
    private ChallengeDifficulty difficulty;
    private LocalDateTime createdAt;
    private LocalDateTime startTime;
    private LocalDateTime deadLine;
    private ChallengeState state;
    private Integer experience;

    /**
     * Complete constructor for challenge reconstitution (e.g., persistence).
     */
    public Challenge(UUID id, String title, String description, ChallengeCategory category,
                     ChallengeDifficulty difficulty, LocalDateTime createdAt, LocalDateTime startTime,
                     LocalDateTime deadLine, ChallengeState state) {
        setId(id);
        setTitle(title);
        setDescription(description);
        setCategory(category);
        setDifficulty(difficulty);
        setCreatedAt(createdAt);
        setStartTime(startTime);
        setDeadLine(deadLine);
        setState(state);
        validateTimeInvariants();
    }

    /**
     * Constructor for creating a new challenge with scheduled/optional availability.
     * Starts in INACTIVE state, with current time as createdAt and generated UUID.
     */
    public Challenge(String title, String description, ChallengeCategory category,
                     ChallengeDifficulty difficulty, LocalDateTime startTime, LocalDateTime deadLine) {
        this(UUID.randomUUID(), title, description, category, difficulty, LocalDateTime.now(), startTime, deadLine, ChallengeState.INACTIVE);
    }

    /**
     * Constructor with explicit id and state, using current time as createdAt.
     */
    public Challenge(UUID id, String title, String description, ChallengeCategory category,
                     ChallengeDifficulty difficulty, LocalDateTime startTime, LocalDateTime deadLine,
                     ChallengeState state) {
        this(id, title, description, category, difficulty, LocalDateTime.now(), startTime, deadLine, state);
    }

    /**
     * Constructor with explicit state, using current time as createdAt and generated UUID.
     */
    public Challenge(String title, String description, ChallengeCategory category,
                     ChallengeDifficulty difficulty, LocalDateTime startTime, LocalDateTime deadLine,
                     ChallengeState state) {
        this(UUID.randomUUID(), title, description, category, difficulty, LocalDateTime.now(), startTime, deadLine, state);
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

    private void setDifficulty(ChallengeDifficulty difficulty) {
        ValidationUtils.requireNonNull(difficulty, "difficulty");
        this.difficulty = difficulty;
        setExperience(ChallengeExperienceCalculator.calculate(difficulty));
    }

    private void setCreatedAt(LocalDateTime createdAt) {
        ValidationUtils.requireNonNull(createdAt, "created at");
        this.createdAt = createdAt;
    }

    private void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    private void setDeadLine(LocalDateTime deadLine) {
        this.deadLine = deadLine;
    }

    private void setState(ChallengeState state) {
        ValidationUtils.requireNonNull(state, "state");
        this.state = state;
    }

    private void setExperience(Integer experience) {
        ValidationUtils.requireNonNegative(experience, "experience");
        this.experience = experience;
    }

    private void validateTimeInvariants() {
        if (this.startTime != null && this.deadLine != null) {
            ValidationUtils.validateTimeOrder(this.startTime, this.deadLine, "Deadline must be after start time");
            if (!this.deadLine.isAfter(this.startTime)) {
                throw new InvalidAttributeException("Deadline must be after start time");
            }
        } else if (this.deadLine != null) {
            ValidationUtils.validateTimeOrder(this.createdAt, this.deadLine, "Deadline cannot be before creation time");
        }
    }

    public void activate() {
        this.state = ChallengeState.ACTIVE;
    }

    public boolean isAvailable(LocalDateTime dateTime) {
        ValidationUtils.requireNonNull(dateTime, "date time");
        if (this.state != ChallengeState.ACTIVE) {
            return false;
        }
        if (this.startTime != null && dateTime.isBefore(this.startTime)) {
            return false;
        }
        if (this.deadLine != null && !dateTime.isBefore(this.deadLine)) {
            return false;
        }
        return true;
    }

    public boolean isAvailable() {
        return isAvailable(LocalDateTime.now());
    }
}
