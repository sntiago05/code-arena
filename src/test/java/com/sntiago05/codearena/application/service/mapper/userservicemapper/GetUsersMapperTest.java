package com.sntiago05.codearena.application.service.mapper.userservicemapper;

import com.sntiago05.codearena.application.exceptions.InvalidCommandException;
import com.sntiago05.codearena.application.ports.in.command.getusers.GetUsersCommand;
import com.sntiago05.codearena.application.ports.in.command.getusers.UserSortField;
import com.sntiago05.codearena.application.ports.in.command.getusers.UserSortOrder;
import com.sntiago05.codearena.application.ports.in.result.GetUsersResult;
import com.sntiago05.codearena.application.ports.out.data.UserPage;
import com.sntiago05.codearena.application.ports.out.data.UserQuery;
import com.sntiago05.codearena.domain.user.User;
import org.junit.jupiter.api.Test;

import java.util.List;

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
    void shouldValidateCommandNotNull() {
        assertThrows(InvalidCommandException.class, () -> GetUsersMapper.toUserQuery(null));
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

    @Test
    void shouldMapToGetUsersResult() {
        User user1 = User.createPlayer("Alice", "alice@example.com", "pass");
        User user2 = User.createPlayer("Bob", "bob@example.com", "pass");

        UserPage userPage = new UserPage(List.of(user1, user2), 0, 10, 2L, 1, false, false);

        GetUsersResult result = GetUsersMapper.toGetUsersResult(userPage);

        assertNotNull(result);
        assertNotNull(result.pagination());
        assertEquals(0, result.pagination().page());
        assertEquals(10, result.pagination().size());
        assertEquals(2L, result.pagination().totalElements());
        assertEquals(1, result.pagination().totalPages());
        assertFalse(result.pagination().hasNext());
        assertFalse(result.pagination().hasPrevious());

        assertEquals(2, result.users().size());
        assertEquals("Alice", result.users().get(0).name());
        assertEquals("alice@example.com", result.users().get(0).email());
        assertEquals("Bob", result.users().get(1).name());
        assertEquals("bob@example.com", result.users().get(1).email());
    }
}
