package com.sntiago05.codearena.application.ports.out.data;

import com.sntiago05.codearena.domain.user.User;

import java.util.List;

public record UserPage(
        List<User> users,
        long totalElements
) {
}
