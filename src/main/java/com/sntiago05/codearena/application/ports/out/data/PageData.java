package com.sntiago05.codearena.application.ports.out.data;

import java.util.List;
import java.util.function.Function;

/**
 * Generic container for paginated query results.
 *
 * @param <T> type of items in the page
 */
public record PageData<T>(
        List<T> content,
        PaginationMeta pagination
) {
    public PageData {
        content = content == null ? List.of() : List.copyOf(content);
    }

    /**
     * Transforms the items of this page into a new type, keeping the exact same pagination metadata.
     *
     * @param mapper function to convert each item from T to R
     * @param <R> the target type
     * @return a new PagedData with transformed content and unchanged pagination
     */
    public <R> PageData<R> map(Function<? super T, R> mapper) {
        return new PageData<>(
                this.content.stream().map(mapper).toList(),
                this.pagination
        );
    }
}
