package com.hades.hadescoffee.application.mappers;

import com.hades.hadescoffee.domain.models.User;
import com.hades.hadescoffee.presentation.dto.requests.auth.SignUpRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AUserMapper {
    @Mapping(target = "password", source = "hashedPassword")
    User signUpRequestToDomain(SignUpRequest request, String hashedPassword);
}
