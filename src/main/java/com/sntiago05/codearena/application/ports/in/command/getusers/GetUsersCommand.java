package com.sntiago05.codearena.application.ports.in.command.getusers;

public record GetUsersCommand(
        Integer page,
        Integer size,
        UserSortField sortField,
        UserSortOrder order
) {
}
