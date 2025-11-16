package com.hades.hadescoffee.application.dtos.results;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class Paginated<T> {
    private final List<T> data;
    private final PagingResult paging;
}
