package com.sntiago05.codearena.application.ports.in;

import com.sntiago05.codearena.application.ports.in.command.GetMyProfileCommand;
import com.sntiago05.codearena.application.ports.in.result.GetMyProfileResult;

public interface GetMyProfileUseCase {

    GetMyProfileResult getMyProfile(GetMyProfileCommand command);
}
