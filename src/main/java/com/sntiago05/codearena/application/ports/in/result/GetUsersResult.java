package com.sntiago05.codearena.application.ports.in.result;

import java.util.List;

/**
 * Result of retrieving a paginated list of users.
 */
public record GetUsersResult(
        PaginationResult pagination,
        List<UserSummaryResult> users
) {
}
