package com.sntiago05.codearena.application.ports.in;

import com.sntiago05.codearena.application.ports.in.command.getusers.GetUsersCommand;
import com.sntiago05.codearena.application.ports.in.result.GetUsersResult;

public interface GetUsersUseCase {
    GetUsersResult getUsers(GetUsersCommand command);
}
