package com.hades.hadescoffee.presentation.controllers.privates;

import com.hades.hadescoffee.application.usecases.GetInvoiceStatusesUseCase;
import com.hades.hadescoffee.presentation.dto.responses.BaseResponse;
import com.hades.hadescoffee.presentation.dto.responses.InvoiceStatusResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Locale;

@RestController
@RequiredArgsConstructor
@RequestMapping(
        value = "private/api/v1/invoice-statuses",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class PrivateInvoiceStatusController {

    private final GetInvoiceStatusesUseCase getInvoiceStatusesUseCase;
    private final MessageSource messageSource;

    @GetMapping
    public BaseResponse<List<InvoiceStatusResponse>> getStatuses(Locale locale) {
        List<InvoiceStatusResponse> response = getInvoiceStatusesUseCase.execute();

        return BaseResponse.<List<InvoiceStatusResponse>>builder()
                .message(messageSource.getMessage("invoiceStatus.get_success", null, locale))
                .data(response)
                .build();
    }
}

