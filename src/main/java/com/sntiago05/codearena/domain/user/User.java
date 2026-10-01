package com.sntiago05.codearena.domain.user;

import com.sntiago05.codearena.domain.utils.ValidationUtils;
import lombok.Getter;

import java.util.UUID;

@Getter
public class User {

    private UUID id;
    private String name;
    private String email;
    private String password;
    private UserRole role;
    private UserLevel level;
    private Integer accumulatedExperience;
    private UserState state;

    public User(UUID id, String name, String email, String password, UserRole role, UserState state, Integer accumulatedExperience) {
        this(name, email, password, role, state, accumulatedExperience);
        setId(id);
    }

    public User(String name, String email, String password, UserRole role, UserState state, Integer accumulatedExperience) {
        setId(UUID.randomUUID());
        setName(name);
        setEmail(email);
        setPassword(password);
        setRole(role);
        setState(state);
        this.accumulatedExperience = 0;
        addExperience(accumulatedExperience);
    }

    public static User createPlayer(String name, String email, String encodedPassword) {
        return new User(name, email, encodedPassword, UserRole.PLAYER, UserState.ACTIVE, 0);
    }

    private void setId(UUID id) {
        ValidationUtils.requireNonNull(id, "Id");
        this.id = id;
    }

    private void setName(String name) {
        ValidationUtils.requireNonEmpty(name, "Name");
        this.name = name;
    }

    private void setEmail(String email) {
        ValidationUtils.requireNonEmpty(email, "Email");
        this.email = email;
    }

    private void setPassword(String password) {
        ValidationUtils.requireNonEmpty(password, "Password");
        this.password = password;
    }

    private void setRole(UserRole role) {
        ValidationUtils.requireNonNull(role, "Role");
        this.role = role;
    }

    public void addExperience(Integer experienceToAdd) {
        ValidationUtils.requireNonNegative(experienceToAdd, "Experience to add");
        this.accumulatedExperience += experienceToAdd;
        this.level = CalculateLevel.calculate(this.accumulatedExperience);
    }

    private void setState(UserState state) {
        ValidationUtils.requireNonNull(state, "State");
        this.state = state;
    }
}
