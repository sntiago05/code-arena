package com.sntiago05.codearena.application.service.mapper.userservicemapper;

import com.sntiago05.codearena.application.exceptions.InvalidCommandException;
import com.sntiago05.codearena.application.ports.in.command.PaginationCommand;
import com.sntiago05.codearena.application.ports.in.command.getusers.GetUsersCommand;
import com.sntiago05.codearena.application.ports.in.command.getusers.UserSortField;
import com.sntiago05.codearena.application.ports.in.command.getusers.UserSortOrder;
import com.sntiago05.codearena.application.ports.in.result.PageResult;
import com.sntiago05.codearena.application.ports.in.result.UserSummaryResult;
import com.sntiago05.codearena.application.ports.out.data.PaginationMeta;
import com.sntiago05.codearena.application.ports.out.data.PageData;
import com.sntiago05.codearena.application.ports.out.data.UserQuery;
import com.sntiago05.codearena.application.ports.out.data.UserSummaryData;
import com.sntiago05.codearena.domain.user.UserRole;
import com.sntiago05.codearena.domain.user.UserState;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class GetUsersMapperTest {

    @Test
    void shouldMapToUserQueryWithDefaults() {
        var command = new GetUsersCommand(0, 10, null, null);

        UserQuery query = GetUsersMapper.toUserQuery(command);

        assertNotNull(query);
        assertEquals(0, query.page());
        assertEquals(10, query.size());
        assertEquals(com.sntiago05.codearena.application.ports.out.data.UserSortField.NAME, query.sortField());
        assertEquals(com.sntiago05.codearena.application.ports.out.data.UserSortOrder.ASCENDING, query.sortOrder());
    }

    @Test
    void shouldMapToUserQueryWithExplicitValues() {
        var command = new GetUsersCommand(2, 20, UserSortField.EMAIL, UserSortOrder.DESCENDING);

        UserQuery query = GetUsersMapper.toUserQuery(command);

        assertNotNull(query);
        assertEquals(2, query.page());
        assertEquals(20, query.size());
        assertEquals(com.sntiago05.codearena.application.ports.out.data.UserSortField.EMAIL, query.sortField());
        assertEquals(com.sntiago05.codearena.application.ports.out.data.UserSortOrder.DESCENDING, query.sortOrder());
    }

    @Test
    void shouldMapToUserQueryWithPaginationCommandDirectly() {
        var pagination = new PaginationCommand(3, 30);
        var command = new GetUsersCommand(pagination, UserSortField.EMAIL, UserSortOrder.DESCENDING);

        UserQuery query = GetUsersMapper.toUserQuery(command);

        assertNotNull(query);
        assertEquals(3, query.page());
        assertEquals(30, query.size());
        assertEquals(com.sntiago05.codearena.application.ports.out.data.UserSortField.EMAIL, query.sortField());
        assertEquals(com.sntiago05.codearena.application.ports.out.data.UserSortOrder.DESCENDING, query.sortOrder());
    }

    @Test
    void shouldValidateCommandNotNull() {
        assertThrows(InvalidCommandException.class, () -> GetUsersMapper.toUserQuery(null));
    }

    @Test
    void shouldValidatePaginationNotNull() {
        assertThrows(InvalidCommandException.class, () -> GetUsersMapper.toUserQuery(new GetUsersCommand((PaginationCommand) null, null, null)));
    }

    @Test
    void shouldValidatePageNotNegativeOrNull() {
        assertThrows(InvalidCommandException.class, () -> GetUsersMapper.toUserQuery(new GetUsersCommand(-1, 10, null, null)));
        assertThrows(InvalidCommandException.class, () -> GetUsersMapper.toUserQuery(new GetUsersCommand(null, 10, null, null)));
    }

    @Test
    void shouldValidateSizePositive() {
        assertThrows(InvalidCommandException.class, () -> GetUsersMapper.toUserQuery(new GetUsersCommand(0, 0, null, null)));
        assertThrows(InvalidCommandException.class, () -> GetUsersMapper.toUserQuery(new GetUsersCommand(0, null, null, null)));
    }

    // ── PageResult contract tests ────────────────────────────────────────────

    @Test
    void shouldMapToPageResult() {
        UserSummaryData user1 = new UserSummaryData(UUID.randomUUID(), "Alice", "alice@example.com", UserRole.PLAYER, UserState.ACTIVE);
        UserSummaryData user2 = new UserSummaryData(UUID.randomUUID(), "Bob", "bob@example.com", UserRole.PLAYER, UserState.ACTIVE);

        PaginationMeta pagination = new PaginationMeta(0, 10, 2L, 1, false, false);
        PageData<UserSummaryData> userPage = new PageData<>(List.of(user1, user2), pagination);

        PageResult<UserSummaryResult> result = GetUsersMapper.toPageResult(userPage);

        assertNotNull(result);
        assertNotNull(result.pagination());
        assertEquals(0, result.pagination().page());
        assertEquals(10, result.pagination().size());
        assertEquals(2L, result.pagination().totalElements());
        assertEquals(1, result.pagination().totalPages());
        assertFalse(result.pagination().hasNext());
        assertFalse(result.pagination().hasPrevious());

        assertEquals(2, result.content().size());
        assertEquals("Alice", result.content().get(0).name());
        assertEquals("alice@example.com", result.content().get(0).email());
        assertEquals("Bob", result.content().get(1).name());
        assertEquals("bob@example.com", result.content().get(1).email());
    }

    @Test
    void shouldNormalizeNullContentToEmptyList() {
        PaginationMeta pagination = new PaginationMeta(0, 10, 0L, 0, false, false);
        PageData<UserSummaryData> userPage = new PageData<>(null, pagination);

        PageResult<UserSummaryResult> result = GetUsersMapper.toPageResult(userPage);

        assertNotNull(result.content());
        assertTrue(result.content().isEmpty());
    }

    @Test
    void shouldReturnUnmodifiableContent() {
        UserSummaryData user = new UserSummaryData(UUID.randomUUID(), "Alice", "alice@example.com", UserRole.PLAYER, UserState.ACTIVE);
        PaginationMeta pagination = new PaginationMeta(0, 10, 1L, 1, false, false);
        PageData<UserSummaryData> userPage = new PageData<>(List.of(user), pagination);

        PageResult<UserSummaryResult> result = GetUsersMapper.toPageResult(userPage);

        assertThrows(UnsupportedOperationException.class,
                () -> result.content().add(new UserSummaryResult("X", "x@x.com", UserRole.PLAYER, UserState.ACTIVE)));
    }

    @Test
    void shouldPreservePaginationMetaDuringMap() {
        UserSummaryData user = new UserSummaryData(UUID.randomUUID(), "Alice", "alice@example.com", UserRole.PLAYER, UserState.ACTIVE);
        PaginationMeta pagination = new PaginationMeta(2, 5, 100L, 20, true, true);
        PageData<UserSummaryData> userPage = new PageData<>(List.of(user), pagination);

        PageResult<UserSummaryResult> result = GetUsersMapper.toPageResult(userPage);
        PageResult<String> mapped = result.map(UserSummaryResult::name);

        assertEquals(result.pagination(), mapped.pagination());
        assertEquals(List.of("Alice"), mapped.content());
    }

    @Test
    void shouldTransformEachElementCorrectly() {
        UserSummaryData user1 = new UserSummaryData(UUID.randomUUID(), "Alice", "alice@example.com", UserRole.PLAYER, UserState.ACTIVE);
        UserSummaryData user2 = new UserSummaryData(UUID.randomUUID(), "Bob", "bob@example.com", UserRole.ADMIN, UserState.INACTIVE);
        PaginationMeta pagination = new PaginationMeta(0, 10, 2L, 1, false, false);
        PageData<UserSummaryData> userPage = new PageData<>(List.of(user1, user2), pagination);

        PageResult<UserSummaryResult> result = GetUsersMapper.toPageResult(userPage);

        assertEquals(2, result.content().size());
        assertEquals(UserRole.PLAYER, result.content().get(0).role());
        assertEquals(UserState.ACTIVE, result.content().get(0).state());
        assertEquals(UserRole.ADMIN, result.content().get(1).role());
        assertEquals(UserState.INACTIVE, result.content().get(1).state());
    }
}
