package com.sntiago05.codearena.application.ports.in.result;

import java.util.List;

public record GetUsersResult(
        PaginationResult pagination,
        List<UserSummaryResult> users
) {
}
