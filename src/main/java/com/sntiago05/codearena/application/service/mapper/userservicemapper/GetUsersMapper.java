package com.sntiago05.codearena.application.service.mapper.userservicemapper;

import com.sntiago05.codearena.application.exceptions.InvalidCommandException;
import com.sntiago05.codearena.application.ports.in.command.getusers.GetUsersCommand;
import com.sntiago05.codearena.application.ports.in.command.getusers.UserSortField;
import com.sntiago05.codearena.application.ports.in.command.getusers.UserSortOrder;
import com.sntiago05.codearena.application.ports.in.result.GetUsersResult;
import com.sntiago05.codearena.application.ports.in.result.PaginationResult;
import com.sntiago05.codearena.application.ports.in.result.UserSummaryResult;
import com.sntiago05.codearena.application.ports.out.data.PageData;
import com.sntiago05.codearena.application.ports.out.data.PaginationMeta;
import com.sntiago05.codearena.application.ports.out.data.UserQuery;
import com.sntiago05.codearena.application.ports.out.data.UserSummaryData;

import java.util.Objects;

/**
 * Mapper utility for transforming user query commands, pagination metadata,
 * and paginated user summaries between application input and output ports.
 */
public final class GetUsersMapper {

    private static final UserSortField DEFAULT_SORT_FIELD = UserSortField.NAME;
    private static final UserSortOrder DEFAULT_SORT_ORDER = UserSortOrder.ASCENDING;

    private GetUsersMapper() {
    }

    /**
     * Converts a {@link GetUsersCommand} into a {@link UserQuery} data port object.
     *
     * @param command the user query command
     * @return the query object for the repository port
     * @throws InvalidCommandException if the command or its parameters are invalid
     */
    public static UserQuery toUserQuery(GetUsersCommand command) {
        validateGetUsersCommand(command);

        var sortField = Objects.requireNonNullElse(command.sortField(), DEFAULT_SORT_FIELD);
        var sortOrder = Objects.requireNonNullElse(command.order(), DEFAULT_SORT_ORDER);

        return new UserQuery(
                command.page(),
                command.size(),
                mapSortField(sortField),
                mapSortOrder(sortOrder)
        );
    }

    /**
     * Converts a paginated list of {@link UserSummaryData} into a {@link GetUsersResult}.
     *
     * @param userPage page containing user summary data and pagination metadata
     * @return the result containing user summaries and pagination
     */
    public static GetUsersResult toGetUsersResult(PageData<UserSummaryData> userPage) {
        Objects.requireNonNull(userPage, "userPage cannot be null");

        PageData<UserSummaryResult> resultPage = userPage.map(GetUsersMapper::toUserSummaryResult);
        PaginationResult pagination = toPaginationResult(resultPage.pagination());

        return new GetUsersResult(pagination, resultPage.content());
    }

    private static UserSummaryResult toUserSummaryResult(UserSummaryData user) {
        return new UserSummaryResult(
                user.name(),
                user.email(),
                user.role(),
                user.state()
        );
    }

    private static PaginationResult toPaginationResult(PaginationMeta pagination) {
        if (pagination == null) {
            return null;
        }

        return new PaginationResult(
                pagination.page(),
                pagination.size(),
                pagination.totalElements(),
                pagination.totalPages(),
                pagination.hasNext(),
                pagination.hasPrevious()
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

    private static com.sntiago05.codearena.application.ports.out.data.UserSortField mapSortField(UserSortField sortField) {
        return switch (sortField) {
            case NAME -> com.sntiago05.codearena.application.ports.out.data.UserSortField.NAME;
            case EMAIL -> com.sntiago05.codearena.application.ports.out.data.UserSortField.EMAIL;
            case STATE -> com.sntiago05.codearena.application.ports.out.data.UserSortField.STATE;
            case ROLE -> com.sntiago05.codearena.application.ports.out.data.UserSortField.ROLE;
        };
    }

    private static com.sntiago05.codearena.application.ports.out.data.UserSortOrder mapSortOrder(UserSortOrder sortOrder) {
        return switch (sortOrder) {
            case ASCENDING -> com.sntiago05.codearena.application.ports.out.data.UserSortOrder.ASCENDING;
            case DESCENDING -> com.sntiago05.codearena.application.ports.out.data.UserSortOrder.DESCENDING;
        };
    }
}
