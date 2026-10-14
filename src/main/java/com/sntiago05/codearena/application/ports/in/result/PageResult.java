package com.sntiago05.codearena.application.ports.in.result;

import java.util.List;
import java.util.function.Function;

/**
 * Generic container for paginated use-case results.
 *
 * <p>Belongs to the input port layer and is independent of the output port
 * contract {@code PageData<T>}. Conversion between the two happens in mappers
 * that bridge output and input ports.
 *
 * @param <T> type of items in the page
 */
public record PageResult<T>(
        PaginationResult pagination,
        List<T> content
) {
    public PageResult {
        content = content == null ? List.of() : List.copyOf(content);
    }

    /**
     * Transforms the items of this page into a new type, keeping the exact
     * same pagination metadata.
     *
     * @param mapper function to convert each item from T to R
     * @param <R>    the target type
     * @return a new {@code PageResult} with transformed content and unchanged pagination
     */
    public <R> PageResult<R> map(Function<? super T, R> mapper) {
        return new PageResult<>(
                this.pagination,
                this.content.stream().map(mapper).toList()
        );
    }
}
