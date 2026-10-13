package com.sntiago05.codearena.application.ports.in.users;

import com.sntiago05.codearena.application.ports.in.command.GetMyProfileCommand;
import com.sntiago05.codearena.application.ports.in.result.GetMyProfileResult;

/**
 * Use case for retrieving the profile of the current user.
 */
public interface GetMyProfileUseCase {

    GetMyProfileResult getMyProfile(GetMyProfileCommand command);
}
