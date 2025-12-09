package com.hades.hadescoffee.presentation.controllers.privates;

import com.hades.hadescoffee.application.usecases.GetRevenueStatsUseCase;
import com.hades.hadescoffee.presentation.dto.responses.BaseResponse;
import com.hades.hadescoffee.presentation.dto.responses.RevenueStatsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
@RequiredArgsConstructor
@RequestMapping("/private/api/v1/stats")
public class PrivateStatsController {
    private final GetRevenueStatsUseCase getRevenueStatsUseCase;

    @GetMapping("/revenue")
    public BaseResponse<RevenueStatsResponse> getRevenueStats(Locale locale) {
        var data = getRevenueStatsUseCase.execute();
        return BaseResponse.<RevenueStatsResponse>builder()
                .data(data)
                .message("")
                .build();
    }
}
