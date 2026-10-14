package com.sntiago05.codearena.application.service.mapper.challengeservicemapper;

import com.sntiago05.codearena.application.ports.in.command.CreateChallengeCommand;
import com.sntiago05.codearena.application.ports.in.result.ChallengeCreated;
import com.sntiago05.codearena.domain.challenge.Challenge;
import com.sntiago05.codearena.domain.challenge.ChallengeCategory;
import com.sntiago05.codearena.domain.challenge.ChallengeDifficulty;
import com.sntiago05.codearena.domain.challenge.ChallengeState;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CreateChallengeMapperTest {

    @Test
    void shouldMapCommandToChallenge() {
        UUID categoryId = UUID.randomUUID();
        ChallengeCategory category = new ChallengeCategory(categoryId, "Algorithms", "Algorithmic challenges");
        LocalDateTime start = LocalDateTime.now().plusDays(1);
        LocalDateTime deadLine = LocalDateTime.now().plusDays(2);

        CreateChallengeCommand command = new CreateChallengeCommand(
                "Two Sum",
                "Find indices summing to target",
                categoryId,
                ChallengeDifficulty.EASY,
                start,
                deadLine
        );

        Challenge challenge = CreateChallengeMapper.toChallenge(command, category);

        assertNotNull(challenge);
        assertNotNull(challenge.getId());
        assertEquals("Two Sum", challenge.getTitle());
        assertEquals("Find indices summing to target", challenge.getDescription());
        assertEquals(category, challenge.getCategory());
        assertEquals(ChallengeDifficulty.EASY, challenge.getDifficulty());
        assertEquals(start, challenge.getStartTime());
        assertEquals(deadLine, challenge.getDeadLine());
        assertEquals(ChallengeState.INACTIVE, challenge.getState());
    }

    @Test
    void shouldMapChallengeToResult() {
        ChallengeCategory category = new ChallengeCategory(UUID.randomUUID(), "Algorithms", "Algorithmic challenges");
        Challenge challenge = new Challenge(
                "Two Sum",
                "Find indices summing to target",
                category,
                ChallengeDifficulty.MEDIUM,
                null,
                null
        );

        ChallengeCreated result = CreateChallengeMapper.toResult(challenge);

        assertNotNull(result);
        assertEquals(challenge.getId(), result.id());
        assertEquals("Two Sum", result.title());
        assertEquals("Find indices summing to target", result.description());
        assertEquals(ChallengeDifficulty.MEDIUM, result.difficulty());
        assertEquals(ChallengeState.INACTIVE, result.state());
    }
}
