package com.hades.hadescoffee.presentation.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PagingResponse {
    int page;
    int size;
    long totalElements;
    int totalPages;
    boolean hasNext;
    boolean hasPrevious;
    boolean isFirst;
    boolean isLast;
}
