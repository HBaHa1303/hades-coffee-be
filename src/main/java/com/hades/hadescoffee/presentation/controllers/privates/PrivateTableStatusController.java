package com.hades.hadescoffee.presentation.controllers.privates;

import com.hades.hadescoffee.application.usecases.GetRolesUseCase;
import com.hades.hadescoffee.application.usecases.GetTableStatusesUseCase;
import com.hades.hadescoffee.presentation.dto.responses.BaseResponse;
import com.hades.hadescoffee.presentation.dto.responses.RoleResponse;
import com.hades.hadescoffee.presentation.dto.responses.TableStatusResponse;
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
        value = "private/api/v1/table-statuses",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class PrivateTableStatusController {

    private final GetTableStatusesUseCase getTableStatusesUseCase;
    private final MessageSource messageSource;

    @GetMapping
    public BaseResponse<List<TableStatusResponse>> getTableStatuses(Locale locale) {
        List<TableStatusResponse> response = getTableStatusesUseCase.execute();

        return BaseResponse.<List<TableStatusResponse>>builder()
                .message(messageSource.getMessage("tableStatus.get_success", null, locale))
                .data(response)
                .build();
    }
}
