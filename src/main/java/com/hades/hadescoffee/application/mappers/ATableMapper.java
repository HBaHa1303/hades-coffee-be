package com.hades.hadescoffee.application.mappers;

import com.hades.hadescoffee.domain.enums.TableStatus;
import com.hades.hadescoffee.domain.models.Table;
import com.hades.hadescoffee.presentation.dto.requests.tables.CreateTableRequest;
import com.hades.hadescoffee.presentation.dto.responses.TableResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ATableMapper {
    @Mapping(target = "status", source = "status")
    Table createRequestToDomain(CreateTableRequest request, TableStatus status);
    TableResponse domainToResponse(Table menuItem);
}
