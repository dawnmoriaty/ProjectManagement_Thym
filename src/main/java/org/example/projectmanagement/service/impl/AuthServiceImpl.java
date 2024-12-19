package org.example.projectmanagement.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.projectmanagement.service.IAuthService;
import org.example.projectmanagement.service.IUserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {
    private final AuthenticationManager authenticationManager;

    public boolean authenticate(String username, String password) {
        try {
            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(username, password);
            Authentication authentication = authenticationManager.authenticate(authToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return true;
        } catch (BadCredentialsException e) {
            return false;
        }
    }

    public String determineRedirectUrl(Authentication authentication) {
        Collection<? extends GrantedAuthority> authorities =
                authentication.getAuthorities();

        if (authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            return "/admin/dashboard";
        } else if (authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_SUPER_ADMIN"))) {
            return "/super-admin/dashboard";
        } else if (authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_EMPLOYEE"))) {
            return "/employee/dashboard";
        } else if (authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_CUSTOMER"))) {
            return "/customer/dashboard";
        }

        return "/home";
    }
}
