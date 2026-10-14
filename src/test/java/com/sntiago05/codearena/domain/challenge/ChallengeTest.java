package com.sntiago05.codearena.domain.challenge;

import com.sntiago05.codearena.domain.exceptions.InvalidAttributeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ChallengeTest {

    private ChallengeCategory category;

    @BeforeEach
    void setUp() {
        category = new ChallengeCategory(UUID.randomUUID(), "Algorithms", "Algorithmic challenges");
    }

    @Test
    void shouldCreateChallengeInInactiveStateWithCalculatedExperience() {
        LocalDateTime start = LocalDateTime.now().plusDays(1);
        LocalDateTime deadline = LocalDateTime.now().plusDays(2);

        Challenge challenge = new Challenge(
                "Two Sum",
                "Find indices of elements adding up to target",
                category,
                ChallengeDifficulty.EASY,
                start,
                deadline
        );

        assertNotNull(challenge.getId());
        assertEquals("Two Sum", challenge.getTitle());
        assertEquals(category, challenge.getCategory());
        assertEquals(ChallengeDifficulty.EASY, challenge.getDifficulty());
        assertEquals(100, challenge.getExperience());
        assertEquals(ChallengeState.INACTIVE, challenge.getState());
        assertNotNull(challenge.getCreatedAt());
        assertEquals(start, challenge.getStartTime());
        assertEquals(deadline, challenge.getDeadLine());
    }

    @Test
    void shouldCalculateExperienceCorrectlyForDifferentDifficulties() {
        Challenge easy = new Challenge("Easy", "Desc", category, ChallengeDifficulty.EASY, null, null);
        Challenge medium = new Challenge("Medium", "Desc", category, ChallengeDifficulty.MEDIUM, null, null);
        Challenge hard = new Challenge("Hard", "Desc", category, ChallengeDifficulty.HARD, null, null);
        Challenge legendary = new Challenge("Legendary", "Desc", category, ChallengeDifficulty.LEGENDARY, null, null);

        assertEquals(100, easy.getExperience());
        assertEquals(250, medium.getExperience());
        assertEquals(500, hard.getExperience());
        assertEquals(1000, legendary.getExperience());
    }

    @Test
    void shouldActivateChallenge() {
        Challenge challenge = new Challenge("Title", "Desc", category, ChallengeDifficulty.EASY, null, null);
        assertEquals(ChallengeState.INACTIVE, challenge.getState());

        challenge.activate();
        assertEquals(ChallengeState.ACTIVE, challenge.getState());
    }

    @Test
    void shouldAllowNullStartTimeAndDeadLine() {
        Challenge challenge = new Challenge("Title", "Desc", category, ChallengeDifficulty.EASY, null, null);
        assertNull(challenge.getStartTime());
        assertNull(challenge.getDeadLine());
    }

    @Test
    void shouldThrowExceptionWhenDeadLineIsBeforeOrEqualToStartTime() {
        LocalDateTime start = LocalDateTime.now().plusDays(2);
        LocalDateTime beforeStart = LocalDateTime.now().plusDays(1);

        assertThrows(InvalidAttributeException.class, () ->
                new Challenge("Title", "Desc", category, ChallengeDifficulty.EASY, start, beforeStart)
        );

        assertThrows(InvalidAttributeException.class, () ->
                new Challenge("Title", "Desc", category, ChallengeDifficulty.EASY, start, start)
        );
    }

    @Test
    void shouldThrowExceptionWhenStartTimeIsNullAndDeadLineIsBeforeCreatedAt() {
        LocalDateTime createdAt = LocalDateTime.now();
        LocalDateTime pastDeadLine = createdAt.minusMinutes(10);

        assertThrows(InvalidAttributeException.class, () ->
                new Challenge(
                        UUID.randomUUID(),
                        "Title",
                        "Desc",
                        category,
                        ChallengeDifficulty.EASY,
                        createdAt,
                        null,
                        pastDeadLine,
                        ChallengeState.INACTIVE
                )
        );
    }

    @Test
    void shouldAllowStartTimeNullWhenDeadLineIsAfterCreatedAt() {
        LocalDateTime createdAt = LocalDateTime.now();
        LocalDateTime futureDeadLine = createdAt.plusDays(5);

        Challenge challenge = new Challenge(
                UUID.randomUUID(),
                "Title",
                "Desc",
                category,
                ChallengeDifficulty.EASY,
                createdAt,
                null,
                futureDeadLine,
                ChallengeState.INACTIVE
        );

        assertNotNull(challenge);
        assertNull(challenge.getStartTime());
        assertEquals(futureDeadLine, challenge.getDeadLine());
    }

    @Test
    void shouldCheckAvailabilityCorrectly() {
        LocalDateTime now = LocalDateTime.of(2026, 10, 13, 12, 0);
        LocalDateTime start = LocalDateTime.of(2026, 10, 13, 10, 0);
        LocalDateTime end = LocalDateTime.of(2026, 10, 13, 18, 0);

        Challenge challenge = new Challenge("Title", "Desc", category, ChallengeDifficulty.MEDIUM, start, end);

        // Not available when INACTIVE
        assertFalse(challenge.isAvailable(now));

        // Available when ACTIVE and within range
        challenge.activate();
        assertTrue(challenge.isAvailable(now));

        // Not available before startTime
        LocalDateTime beforeStart = LocalDateTime.of(2026, 10, 13, 9, 0);
        assertFalse(challenge.isAvailable(beforeStart));

        // Not available at or after deadLine
        assertFalse(challenge.isAvailable(end));
        LocalDateTime afterEnd = LocalDateTime.of(2026, 10, 13, 19, 0);
        assertFalse(challenge.isAvailable(afterEnd));
    }

    @Test
    void shouldBeAvailableImmediatelyAfterActivationWhenStartTimeIsNull() {
        LocalDateTime now = LocalDateTime.of(2026, 10, 13, 12, 0);
        Challenge challenge = new Challenge("Title", "Desc", category, ChallengeDifficulty.EASY, null, null);

        assertFalse(challenge.isAvailable(now));
        challenge.activate();
        assertTrue(challenge.isAvailable(now));
    }
}
