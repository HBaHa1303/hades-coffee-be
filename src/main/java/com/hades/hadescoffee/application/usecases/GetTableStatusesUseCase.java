package com.hades.hadescoffee.application.usecases;

import com.hades.hadescoffee.domain.enums.TableStatus;
import com.hades.hadescoffee.presentation.dto.responses.TableStatusResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GetTableStatusesUseCase {

    public List<TableStatusResponse> execute() {
        return Arrays.stream(TableStatus.values())
                .map(TableStatusResponse::new)
                .toList();
    }
}
