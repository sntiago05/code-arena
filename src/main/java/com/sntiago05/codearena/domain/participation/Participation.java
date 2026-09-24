package com.sntiago05.codearena.domain.participation;

import com.sntiago05.codearena.domain.exceptions.InvalidParticipationStateException;
import com.sntiago05.codearena.domain.utils.ValidationUtils;

import java.time.LocalDateTime;
import java.util.UUID;

public class Participation {

    private UUID id;
    private UUID userId;
    private UUID challengeId;
    private LocalDateTime startedAt;
    private LocalDateTime submittedAt;
    private ParticipationState state;
    private String solution;
    private Integer exp;

    private Participation(UUID id, UUID userId, UUID challengeId) {
        ValidationUtils.requireNonNull(id, "id");
        ValidationUtils.requireNonNull(userId, "userId");
        ValidationUtils.requireNonNull(challengeId, "challengeId");

        this.id = id;
        this.userId = userId;
        this.challengeId = challengeId;
        this.state = ParticipationState.ACCEPTED;
    }

    public static Participation create(UUID id, UUID userId, UUID challengeId) {
        return new Participation(id, userId, challengeId);
    }

    public Participation start() {
        if (this.state != ParticipationState.ACCEPTED)
            throw new InvalidParticipationStateException("Cannot start: state must be ACCEPTED");

        this.state = ParticipationState.IN_PROGRESS;
        this.startedAt = LocalDateTime.now();
        return this;
    }

    public Participation submitSolution(String solution) {
        ValidationUtils.requireNonEmpty(solution, "solution");
        if (this.state != ParticipationState.IN_PROGRESS)
            throw new InvalidParticipationStateException("Cannot submit: state must be ACCEPTED or IN_PROGRESS");

        this.solution = solution;
        this.state = ParticipationState.SUBMITTED;
        this.submittedAt = LocalDateTime.now();
        return this;
    }

    public Participation approve(Integer exp) {
        if (this.state != ParticipationState.SUBMITTED)
            throw new InvalidParticipationStateException("Cannot approve: state must be SUBMITTED");

        this.state = ParticipationState.APPROVED;
        grantExperience(exp);
        return this;
    }

    public Participation reject() {
        if (this.state != ParticipationState.SUBMITTED)
            throw new InvalidParticipationStateException("Cannot reject: state must be SUBMITTED");

        this.state = ParticipationState.REJECTED;
        return this;
    }

    private void grantExperience(Integer exp) {
        ValidationUtils.requireNonNegative(exp, "exp");
        if (this.state != ParticipationState.APPROVED)
            throw new InvalidParticipationStateException("Cannot grant experience: state must be APPROVED");
        if (this.exp != null)
            throw new InvalidParticipationStateException("Cannot grant experience: exp is already granted");
        this.exp = exp;
    }
}
