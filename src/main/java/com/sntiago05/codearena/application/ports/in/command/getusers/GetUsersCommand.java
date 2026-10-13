package com.sntiago05.codearena.application.ports.in.command.getusers;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

/**
 * Command for retrieving a paginated and sorted list of users.
 */
public record GetUsersCommand(
        @NotNull(message = "Page cannot be null")
        @Min(value = 0, message = "Page index must be greater than or equal to zero")
        Integer page,

        @NotNull(message = "Size cannot be null")
        @Min(value = 1, message = "Page size must be greater than zero")
        Integer size,

        UserSortField sortField,
        UserSortOrder order
) {
}
