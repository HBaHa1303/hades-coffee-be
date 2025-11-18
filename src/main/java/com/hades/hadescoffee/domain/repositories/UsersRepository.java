package com.hades.hadescoffee.domain.repositories;

import com.hades.hadescoffee.application.dtos.results.Paginated;
import com.hades.hadescoffee.domain.enums.TableStatus;
import com.hades.hadescoffee.domain.models.Invoice;
import com.hades.hadescoffee.domain.models.Role;
import com.hades.hadescoffee.domain.models.Table;
import com.hades.hadescoffee.domain.models.User;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UsersRepository {
    Optional<User> findById(Long id);
    void save(User user);
    Optional<User> findByEmail(String email);
    Optional<User> findByEmailWithRoles(String email);

    void delete(User user);
    Paginated<User> findAllByKeywordAndRole(String keyword, Pageable pageable);
}
