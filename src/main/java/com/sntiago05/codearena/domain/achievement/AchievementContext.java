package com.sntiago05.codearena.domain.achievement;

import com.sntiago05.codearena.domain.challenge.Challenge;
import com.sntiago05.codearena.domain.participation.Participation;
import com.sntiago05.codearena.domain.user.User;
import com.sntiago05.codearena.domain.utils.ValidationUtils;
import lombok.Getter;

import java.util.List;

public class AchievementContext {
    @Getter
    private User user;
    private List<Participation> participations;
    private List<Challenge> challenges;

    public AchievementContext(User user, List<Participation> participations, List<Challenge> challenges) {
        setUser(user);
        setParticipations(participations);
        setChallenges(challenges);
    }

    private void setUser(User user) {
        ValidationUtils.requireNonNull(user, "user");
        this.user = user;
    }

    private void setParticipations(List<Participation> participations) {
        ValidationUtils.requireNonNull(participations, "participations");
        this.participations = participations;
    }

    private void setChallenges(List<Challenge> challenges) {
        ValidationUtils.requireNonNull(challenges, "challenges");
        this.challenges = challenges;
    }

    public List<Participation> getParticipations() {
        return List.copyOf(participations);
    }

    public List<Challenge> getChallenges() {
        return List.copyOf(challenges);
    }
}
