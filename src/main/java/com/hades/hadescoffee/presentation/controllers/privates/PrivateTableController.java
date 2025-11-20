package com.hades.hadescoffee.presentation.controllers.privates;

import com.hades.hadescoffee.application.usecases.table.*;
import com.hades.hadescoffee.presentation.dto.requests.tables.ChangeTableStatusRequest;
import com.hades.hadescoffee.presentation.dto.requests.tables.CreateTableRequest;
import com.hades.hadescoffee.presentation.dto.requests.tables.UpdateTableRequest;
import com.hades.hadescoffee.presentation.dto.requests.tables.FilterTablesRequest;
import com.hades.hadescoffee.presentation.dto.responses.BaseResponse;
import com.hades.hadescoffee.presentation.dto.responses.TableResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "private/api/v1/tables",
        produces = MediaType.APPLICATION_JSON_VALUE)
public class PrivateTableController {

    private final MessageSource messageSource;

    private final CreateTableUseCase createUseCase;
    private final UpdateTableUseCase updateUseCase;
    private final DeleteTableUseCase deleteUseCase;
    private final GetTableByIdUseCase getByIdUseCase;
    private final GetTablesUseCase getUseCase;
    private final ChangeTableStatusUseCase changeStatusUseCase;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<Object> createTable(@RequestBody CreateTableRequest request, Locale locale) {
        createUseCase.execute(request);
        return BaseResponse.builder()
                .message(messageSource.getMessage("table.created_success", null, locale))
                .build();
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<Object> updateTable(@PathVariable Long id, @RequestBody UpdateTableRequest request, Locale locale) {
        updateUseCase.execute(id, request);
        return BaseResponse.builder()
                .message(messageSource.getMessage("table.updated_success", null, locale))
                .build();
    }

    @PatchMapping(value = "/{id}/status", consumes = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<Object> changeStatus(@PathVariable Long id, @RequestBody ChangeTableStatusRequest request, Locale locale) {
        changeStatusUseCase.execute(id, request);
        return BaseResponse.builder()
                .message(messageSource.getMessage("table.status_changed_success", null, locale))
                .build();
    }

    @DeleteMapping("/{id}")
    public BaseResponse<Object> deleteTable(@PathVariable Long id, Locale locale) {
        deleteUseCase.execute(id);
        return BaseResponse.builder()
                .message(messageSource.getMessage("table.deleted_success", null, locale))
                .build();
    }

    @GetMapping("/{id}")
    public BaseResponse<TableResponse> getById(@PathVariable Long id, Locale locale) {
        var data = getByIdUseCase.execute(id);
        return BaseResponse.<TableResponse>builder()
                .data(data)
                .message(messageSource.getMessage("table.get_by_id_success", null, locale))
                .build();
    }

    @GetMapping
    public BaseResponse<List<TableResponse>> list(FilterTablesRequest request, Locale locale) {
        var data = getUseCase.execute(request);
        return BaseResponse.<List<TableResponse>>builder()
                .data(data.getData())
                .paging(data.getPaging())
                .message(messageSource.getMessage("table.get_success", null, locale))
                .build();
    }
}
