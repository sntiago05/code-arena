package com.sntiago05.codearena.application.ports.in.result;

public record PaginationResult(
        int page,
        int size,
        long totalElements,
        int totalPages,
        boolean hasNext,
        boolean hasPrevious
) {
}
