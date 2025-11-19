package com.hades.hadescoffee.application.usecases.table;

import com.hades.hadescoffee.application.dtos.results.Paginated;
import com.hades.hadescoffee.application.dtos.results.PagingResult;
import com.hades.hadescoffee.application.mappers.ATableMapper;
import com.hades.hadescoffee.application.utils.PageableUtil;
import com.hades.hadescoffee.domain.models.Table;
import com.hades.hadescoffee.domain.repositories.TablesRepository;
import com.hades.hadescoffee.presentation.dto.requests.tables.FilterTablesRequest;
import com.hades.hadescoffee.presentation.dto.responses.TableResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GetTablesUseCase {
    private final TablesRepository tablesRepository;
    private final ATableMapper mapper;

    public Paginated<TableResponse> execute(FilterTablesRequest request) {
        Pageable pageable = PageableUtil.toPageable(request);
        Paginated<Table> domainPage = tablesRepository.findAllByKeywordAndStatus(
                request.getKeyword(), request.getStatus(), pageable);
        List<TableResponse> responseList = domainPage.getData().stream()
                .map(mapper::domainToResponse)
                .toList();
        PagingResult paging = domainPage.getPaging();
        return new Paginated<>(responseList, paging);
    }
}
