package com.sntiago05.codearena.application.ports.in.command.getusers;

import com.sntiago05.codearena.application.ports.in.command.PaginationCommand;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

/**
 * Command for retrieving a paginated and sorted list of users.
 */
public record GetUsersCommand(
        @NotNull(message = "Pagination cannot be null")
        @Valid
        PaginationCommand pagination,

        UserSortField sortField,
        UserSortOrder order
) {
    /**
     * Convenience constructor allowing direct page and size parameters.
     */
    public GetUsersCommand(Integer page, Integer size, UserSortField sortField, UserSortOrder order) {
        this(new PaginationCommand(page, size), sortField, order);
    }

    public Integer page() {
        return pagination != null ? pagination.page() : null;
    }

    public Integer size() {
        return pagination != null ? pagination.size() : null;
    }
}
