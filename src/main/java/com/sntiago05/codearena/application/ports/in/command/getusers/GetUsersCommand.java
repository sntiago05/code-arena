package com.sntiago05.codearena.application.ports.in.command.getusers;

/**
 * Command for retrieving a paginated and sorted list of users.
 */
public record GetUsersCommand(
        Integer page,
        Integer size,
        UserSortField sortField,
        UserSortOrder order
) {
}
