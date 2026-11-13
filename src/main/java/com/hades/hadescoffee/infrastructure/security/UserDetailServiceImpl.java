package com.hades.hadescoffee.infrastructure.security;

import com.hades.hadescoffee.domain.models.User;
import com.hades.hadescoffee.domain.repositories.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {
    private final UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = usersRepository.findByIdWithRoles(Long.parseLong(username))
                    .orElseThrow(() -> new UsernameNotFoundException(username));

        return new UserDetailsImpl(
                user.getId(),
                user.getPassword().getHashed(),
                getGrantedAuthorities(user)
                );
    }

    private List<SimpleGrantedAuthority> getGrantedAuthorities(User user) {
        return user.getRoleCodes().stream()
                .map(SimpleGrantedAuthority::new)
                .toList();
    }
}
