package com.sntiago05.codearena.application.ports.out.data;
/**
 * Query parameters for paginated and sorted user searches.
 */
public record UserQuery(
        int page,
        int size,
        UserSortField sortField,
        UserSortOrder sortOrder
) {
}
