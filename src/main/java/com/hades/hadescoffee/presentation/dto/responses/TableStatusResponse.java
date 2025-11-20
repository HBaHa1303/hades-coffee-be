package com.hades.hadescoffee.presentation.dto.responses;

import com.hades.hadescoffee.domain.enums.TableStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class TableStatusResponse {
    TableStatus status;
}
