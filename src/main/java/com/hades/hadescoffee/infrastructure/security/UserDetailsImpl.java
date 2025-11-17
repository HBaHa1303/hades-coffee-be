package com.hades.hadescoffee.infrastructure.security;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDetailsImpl implements UserDetails {
    String email;
    String password;
    List<SimpleGrantedAuthority> grantedAuthorities;

    public UserDetailsImpl(String email, String password, List<SimpleGrantedAuthority> grantedAuthorities) {
        if (StringUtils.isBlank(email)) {
            // todo: validate
        }

        if (Objects.isNull(grantedAuthorities)) {
            //TODO: validate
        }

        this.email = email;
        this.password = password;
        this.grantedAuthorities = grantedAuthorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }
}
