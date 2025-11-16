package com.hades.hadescoffee.application.dtos.results;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class Paginated<T> {
    private final List<T> data;
    private final PagingResult paging;
}
