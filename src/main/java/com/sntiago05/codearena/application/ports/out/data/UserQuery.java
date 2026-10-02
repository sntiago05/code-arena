package com.sntiago05.codearena.application.ports.out.data;
public record UserQuery(
        int page,
        int size,
        UserSortField sortField,
        UserSortOrder sortOrder
) {
}
