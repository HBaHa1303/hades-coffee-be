package com.hades.hadescoffee.application.usecases;

import com.hades.hadescoffee.domain.enums.InvoiceStatus;
import com.hades.hadescoffee.presentation.dto.responses.InvoiceStatusResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GetInvoiceStatusesUseCase {

    public List<InvoiceStatusResponse> execute() {
        return Arrays.stream(InvoiceStatus.values())
                .map(InvoiceStatusResponse::new)
                .toList();
    }
}
