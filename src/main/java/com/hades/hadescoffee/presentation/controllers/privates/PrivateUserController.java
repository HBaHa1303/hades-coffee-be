package com.hades.hadescoffee.presentation.controllers.privates;

import com.hades.hadescoffee.application.usecases.user.*;
import com.hades.hadescoffee.presentation.dto.requests.user.*;
import com.hades.hadescoffee.presentation.dto.responses.BaseResponse;
import com.hades.hadescoffee.presentation.dto.responses.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "private/api/v1/users",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE)
public class PrivateUserController {

    private final MessageSource messageSource;

    private final CreateUserUseCase createUseCase;
    private final DeleteUserUseCase deleteUseCase;
    private final GetUserByIdUseCase getByIdUseCase;
    private final GetUsersUseCase getUseCase;
    private final ChangeUserPasswordUseCase changePasswordUseCase;
    private final ChangeUserRolesUseCase changeUserRolesUseCase;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public BaseResponse<Object> createUser(@RequestBody CreateUserRequest request, Locale locale) {
        createUseCase.execute(request);
        return BaseResponse.builder()
                .message(messageSource.getMessage("user.created_success", null, locale))
                .build();
    }

    @PatchMapping("/{id}/password")
    public BaseResponse<Object> changePassword(@PathVariable Long id, @RequestBody ChangeUserPasswordRequest request, Locale locale) {
        changePasswordUseCase.execute(id, request);
        return BaseResponse.builder()
                .message(messageSource.getMessage("user.password_changed_success", null, locale))
                .build();
    }

    @PatchMapping("/{id}/roles")
    public BaseResponse<Object> changeRoles(@PathVariable Long id, @RequestBody ChangeUserRoleRequest request, Locale locale) {
        changeUserRolesUseCase.execute(id, request);
        return BaseResponse.builder()
                .message(messageSource.getMessage("user.role_changed_success", null, locale))
                .build();
    }

    @DeleteMapping("/{id}")
    public BaseResponse<Object> deleteUser(@PathVariable Long id, Locale locale) {
        deleteUseCase.execute(id);
        return BaseResponse.builder()
                .message(messageSource.getMessage("user.deleted_success", null, locale))
                .build();
    }

    @GetMapping("/{id}")
    public BaseResponse<UserResponse> getById(@PathVariable Long id, Locale locale) {
        var data = getByIdUseCase.execute(id);
        return BaseResponse.<UserResponse>builder()
                .data(data)
                .message(messageSource.getMessage("user.get_by_id_success", null, locale))
                .build();
    }

    @GetMapping
    public BaseResponse<List<UserResponse>> list(FilterUsersRequest request, Locale locale) {
        var data = getUseCase.execute(request);
        return BaseResponse.<List<UserResponse>>builder()
                .data(data.getData())
                .paging(data.getPaging())
                .message(messageSource.getMessage("user.get_success", null, locale))
                .build();
    }
}
