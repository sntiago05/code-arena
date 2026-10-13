package com.sntiago05.codearena.application.service.mapper.userservicemapper;

import com.sntiago05.codearena.application.exceptions.InvalidCommandException;
import com.sntiago05.codearena.application.ports.in.command.getusers.GetUsersCommand;
import com.sntiago05.codearena.application.ports.in.command.getusers.UserSortField;
import com.sntiago05.codearena.application.ports.in.command.getusers.UserSortOrder;
import com.sntiago05.codearena.application.ports.in.result.GetUsersResult;
import com.sntiago05.codearena.application.ports.in.result.PaginationResult;
import com.sntiago05.codearena.application.ports.in.result.UserSummaryResult;
import com.sntiago05.codearena.application.ports.out.data.UserPage;
import com.sntiago05.codearena.application.ports.out.data.UserQuery;

import java.util.List;

public final class GetUsersMapper {

    private GetUsersMapper() {}

    public static UserQuery toUserQuery(GetUsersCommand command) {
        validateGetUsersCommand(command);

        var inSortField = command.sortField() != null ? command.sortField() : UserSortField.NAME;
        var inSortOrder = command.order() != null ? command.order() : UserSortOrder.ASCENDING;

        return new UserQuery(
                command.page(),
                command.size(),
                mapSortField(inSortField),
                mapSortOrder(inSortOrder)
        );
    }

    public static GetUsersResult toGetUsersResult(UserPage userPage) {
        PaginationResult pagination = getPagination(userPage);

        List<UserSummaryResult> users = userPage.users().stream()
                .map(user -> new UserSummaryResult(
                        user.getName(),
                        user.getEmail(),
                        user.getRole(),
                        user.getState()
                ))
                .toList();

        return new GetUsersResult(pagination, users);
    }

    public static GetUsersResult toResult(UserPage userPage) {
        return toGetUsersResult(userPage);
    }

    private static PaginationResult getPagination(UserPage userPage) {
        return new PaginationResult(
                userPage.page(),
                userPage.size(),
                userPage.totalElements(),
                userPage.totalPages(),
                userPage.hasNext(),
                userPage.hasPrevious()
        );
    }

    private static void validateGetUsersCommand(GetUsersCommand command) {
        if (command == null) {
            throw new InvalidCommandException("Command cannot be null");
        }

        Integer page = command.page();
        Integer size = command.size();

        if (page == null || page < 0) {
            throw new InvalidCommandException("Page index must not be null and must be greater than or equal to zero");
        }
        if (size == null || size < 1) {
            throw new InvalidCommandException("Page size must not be null and must be greater than zero");
        }
    }

    private static com.sntiago05.codearena.application.ports.out.data.UserSortField mapSortField(UserSortField inSortField) {
        return switch (inSortField) {
            case NAME -> com.sntiago05.codearena.application.ports.out.data.UserSortField.NAME;
            case EMAIL -> com.sntiago05.codearena.application.ports.out.data.UserSortField.EMAIL;
            case STATE -> com.sntiago05.codearena.application.ports.out.data.UserSortField.STATE;
            case ROLE -> com.sntiago05.codearena.application.ports.out.data.UserSortField.ROLE;
        };
    }

    private static com.sntiago05.codearena.application.ports.out.data.UserSortOrder mapSortOrder(UserSortOrder inSortOrder) {
        return switch (inSortOrder) {
            case ASCENDING -> com.sntiago05.codearena.application.ports.out.data.UserSortOrder.ASCENDING;
            case DESCENDING -> com.sntiago05.codearena.application.ports.out.data.UserSortOrder.DESCENDING;
        };
    }
}
