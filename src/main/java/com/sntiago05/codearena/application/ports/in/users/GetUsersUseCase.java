package com.sntiago05.codearena.application.ports.in.users;

import com.sntiago05.codearena.application.ports.in.command.getusers.GetUsersCommand;
import com.sntiago05.codearena.application.ports.in.result.PageResult;
import com.sntiago05.codearena.application.ports.in.result.UserSummaryResult;

/**
 * Use case for retrieving a paginated list of users.
 */
public interface GetUsersUseCase {
    PageResult<UserSummaryResult> getUsers(GetUsersCommand command);
}
