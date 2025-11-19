package com.hades.hadescoffee.presentation.dto.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hades.hadescoffee.application.dtos.results.PagingResult;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Map;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BaseResponse<T> {
    String message;
    Map<String, String> errors;
    PagingResult paging;
    T data;
}
