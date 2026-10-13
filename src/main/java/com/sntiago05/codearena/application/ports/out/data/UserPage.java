package com.sntiago05.codearena.application.ports.out.data;

import com.sntiago05.codearena.domain.user.User;

import java.util.List;

/**
 * A paginated collection of users.
 */
public record UserPage(
        List<User> users,
        int page,
        int size,
        long totalElements,
        int totalPages,
        boolean hasNext,
        boolean hasPrevious
) {
}
