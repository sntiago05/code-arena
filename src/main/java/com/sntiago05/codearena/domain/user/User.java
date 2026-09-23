package com.sntiago05.codearena.domain.user;

import com.sntiago05.codearena.domain.exceptions.EmptyAttributeException;
import com.sntiago05.codearena.domain.exceptions.NegativeAttributeException;
import com.sntiago05.codearena.domain.exceptions.NullAttributeException;

import java.util.UUID;

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
        setId(id);
        setName(name);
        setEmail(email);
        setPassword(password);
        setRole(role);
        setState(state);
        this.accumulatedExperience =0;
        addExperience(accumulatedExperience);
    }

    private void setId(UUID id) {
        if (id == null) {
            throw new NullAttributeException("Id");
        }
        this.id = id;
    }

    private void setName(String name) {
        if (name == null) {
            throw new NullAttributeException("Name");
        }
        if (name.trim().isEmpty()) {
            throw new EmptyAttributeException("Name");
        }
        this.name = name;
    }

    private void setEmail(String email) {
        if (email == null) {
            throw new NullAttributeException("Email");
        }
        if (email.trim().isEmpty()) {
            throw new EmptyAttributeException("Email");
        }
        this.email = email;
    }

    private void setPassword(String password) {
        if (password == null) {
            throw new NullAttributeException("Password");
        }
        if (password.trim().isEmpty()) {
            throw new EmptyAttributeException("Password");
        }
        this.password = password;
    }

    private void setRole(UserRole role) {
        if (role == null) {
            throw new NullAttributeException("Role");
        }
        this.role = role;
    }

    public void addExperience(Integer experienceToAdd) {
        if (experienceToAdd == null) {
            throw new NullAttributeException("Experience to add");
        }
        if (experienceToAdd < 0) {
            throw new NegativeAttributeException("Experience to add");
        }
        this.accumulatedExperience += experienceToAdd;
        this.level = CalculateLevel.calculate(this.accumulatedExperience);
    }

    private void setState(UserState state) {
        if (state == null) {
            throw new NullAttributeException("State");
        }
        this.state = state;
    }
}
