package com.sntiago05.codearena.application.service.mapper.userservicemapper;

import com.sntiago05.codearena.application.ports.in.result.GetMyProfileResult;
import com.sntiago05.codearena.domain.user.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GetMyProfileMapperTest {

    @Test
    void shouldMapToGetMyProfileResult() {
        var user = User.createPlayer("Jane Doe", "jane@example.com", "secret");
        long completedParticipations = 10L;
        long acceptedParticipations = 7L;

        GetMyProfileResult result = GetMyProfileMapper.toGetMyProfileResult(user, completedParticipations, acceptedParticipations);

        assertNotNull(result);
        assertEquals("Jane Doe", result.name());
        assertEquals("jane@example.com", result.email());
        assertEquals(user.getAccumulatedExperience(), result.experience());
        assertEquals(user.getLevel(), result.userLevel());
        assertEquals(10L, result.challengesCompleted());
        assertEquals(7L, result.challengesAccepted());
    }
}
