package com.sntiago05.codearena.domain.challenge;

import com.sntiago05.codearena.domain.utils.ValidationUtils;

import java.util.UUID;

/**
 * Represents a category for organizing challenges.
 */
public class ChallengeCategory {

    private UUID id;
    private String name;
    private String description;

    public ChallengeCategory(UUID id, String name, String description) {
        setId(id);
        setName(name);
        setDescription(description);
    }

    private void setId(UUID id) {
        ValidationUtils.requireNonNull(id, "id");
        this.id = id;
    }

    public void setName(String name) {
        ValidationUtils.requireNonEmpty(name, "name");
        this.name = name;
    }

    public void setDescription(String description) {
        ValidationUtils.requireNonEmpty(description, "description");
        this.description = description;
    }
}
