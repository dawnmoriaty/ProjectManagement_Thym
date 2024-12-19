package org.example.projectmanagement.security;

import lombok.*;
import org.example.projectmanagement.model.entity.Users;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserPrinciple implements UserDetails {

    private Users users;

    public UserPrinciple(Users users) {
        this.users = users;
    }

    private Collection<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(
                new SimpleGrantedAuthority(users.getRoles().toString())
        );
    }

    @Override
    public String getPassword() {
        return this.users.getPassword();
    }

    @Override
    public String getUsername() {
        return this.users.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}