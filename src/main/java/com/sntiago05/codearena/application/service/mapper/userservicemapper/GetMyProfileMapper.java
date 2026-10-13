package com.sntiago05.codearena.application.service.mapper.userservicemapper;

import com.sntiago05.codearena.application.ports.in.result.GetMyProfileResult;
import com.sntiago05.codearena.domain.user.User;

public final class GetMyProfileMapper {

    private GetMyProfileMapper() {}

    public static GetMyProfileResult toGetMyProfileResult(User user, long completedParticipations, long acceptedParticipations) {
        return new GetMyProfileResult(
                user.getName(),
                user.getEmail(),
                user.getAccumulatedExperience(),
                user.getLevel(),
                completedParticipations,
                acceptedParticipations
        );
    }

    public static GetMyProfileResult toResult(User user, long completedParticipations, long acceptedParticipations) {
        return toGetMyProfileResult(user, completedParticipations, acceptedParticipations);
    }
}
