package com.sntiago05.codearena.domain.participation;

import com.sntiago05.codearena.domain.challenge.Challenge;
import com.sntiago05.codearena.domain.exceptions.InvalidParticipationStateException;
import com.sntiago05.codearena.domain.utils.ValidationUtils;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;
@Getter
/** Represents a user's participation attempt in a challenge. */
public class Participation {

    private UUID id;
    private UUID userId;
    private Challenge challenge;
    private LocalDateTime startedAt;
    private LocalDateTime submittedAt;
    private ParticipationState state;
    private String solution;
    private Integer exp;

    private Participation(UUID id, UUID userId, Challenge challenge) {
        setId(id);
        setUserId(userId);
        setChallenge(challenge);
        setState(ParticipationState.ACCEPTED);
    }

    private void setId(UUID id) {
        ValidationUtils.requireNonNull(id, "id");
        this.id = id;
    }

    private void setUserId(UUID userId) {
        ValidationUtils.requireNonNull(userId, "userId");
        this.userId = userId;
    }

    private void setChallenge(Challenge challenge) {
        ValidationUtils.requireNonNull(challenge, "challenge");
        this.challenge = challenge;
    }

    private void setState(ParticipationState state) {
        ValidationUtils.requireNonNull(state, "state");
        this.state = state;
    }

    public static Participation create(UUID id, UUID userId, Challenge challenge) {
        return new Participation(id, userId, challenge);
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
