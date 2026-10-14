package com.sntiago05.codearena.application.ports.in.command;

import com.sntiago05.codearena.application.exceptions.InvalidCommandException;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

/**
 * Reusable command representing pagination parameters.
 */
public record PaginationCommand(
        @NotNull(message = "Page cannot be null")
        @Min(value = 0, message = "Page index must be greater than or equal to zero")
        Integer page,

        @NotNull(message = "Size cannot be null")
        @Min(value = 1, message = "Page size must be greater than zero")
        Integer size
) {
    public static final int DEFAULT_PAGE = 0;
    public static final int DEFAULT_SIZE = 10;

    public static PaginationCommand of(Integer page, Integer size) {
        return new PaginationCommand(page, size);
    }

    public static PaginationCommand defaultPagination() {
        return new PaginationCommand(DEFAULT_PAGE, DEFAULT_SIZE);
    }

    /**
     * Validates pagination parameters and throws {@link InvalidCommandException} if invalid.
     */
    public void validate() {
        if (page == null || page < 0) {
            throw new InvalidCommandException("Page index must not be null and must be greater than or equal to zero");
        }
        if (size == null || size < 1) {
            throw new InvalidCommandException("Page size must not be null and must be greater than zero");
        }
    }
}
