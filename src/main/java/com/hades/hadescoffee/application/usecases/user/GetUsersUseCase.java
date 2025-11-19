package com.hades.hadescoffee.application.usecases.user;

import com.hades.hadescoffee.application.dtos.results.Paginated;
import com.hades.hadescoffee.application.dtos.results.PagingResult;
import com.hades.hadescoffee.application.mappers.AUserMapper;
import com.hades.hadescoffee.application.utils.PageableUtil;
import com.hades.hadescoffee.domain.models.User;
import com.hades.hadescoffee.domain.repositories.UsersRepository;
import com.hades.hadescoffee.presentation.dto.requests.user.FilterUsersRequest;
import com.hades.hadescoffee.presentation.dto.responses.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GetUsersUseCase {
    private final UsersRepository usersRepository;
    private final AUserMapper mapper;

    public Paginated<UserResponse> execute(FilterUsersRequest request) {
        Pageable pageable = PageableUtil.toPageable(request);
        Paginated<User> domainPage = usersRepository.findAllByKeywordAndRole(
                request.getKeyword(), pageable);
        List<UserResponse> responseList = domainPage.getData().stream()
                .map(mapper::domainToResponse)
                .toList();
        PagingResult paging = domainPage.getPaging();
        return new Paginated<>(responseList, paging);
    }
}
