package com.hades.hadescoffee.presentation.dto.requests.user;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChangeUserRoleRequest {
    Set<String> roleCodes;
}