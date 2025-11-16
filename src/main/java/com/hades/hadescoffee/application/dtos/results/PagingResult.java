package com.hades.hadescoffee.application.dtos.results;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PagingResult {
    int page;
    int size;
    long totalElements;
    int totalPages;
    boolean hasNext;
    boolean hasPrevious;
    boolean isFirst;
    boolean isLast;
}
