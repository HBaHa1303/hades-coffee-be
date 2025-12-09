package com.hades.hadescoffee.presentation.dto.responses;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RevenueStatsResponse {
    BigDecimal day;
    BigDecimal week;
    BigDecimal month;
}
