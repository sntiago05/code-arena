package com.sntiago05.codearena.application.ports.out;

import com.sntiago05.codearena.application.ports.out.data.PageData;
import com.sntiago05.codearena.application.ports.out.data.UserQuery;
import com.sntiago05.codearena.application.ports.out.data.UserSummaryData;
import com.sntiago05.codearena.domain.user.User;

import java.util.Optional;
import java.util.UUID;

/**
 * Port defining operations for user persistence.
 */
public interface UserRepositoryPort {
    Optional<User> findByEmail(String email);
    Optional<User> findById(UUID id);
    User save(User user);
    boolean existsByEmail(String email);
    PageData<UserSummaryData> findPage(UserQuery query);
}
