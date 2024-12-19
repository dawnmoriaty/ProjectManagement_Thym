package org.example.projectmanagement.service;

import org.springframework.security.core.Authentication;

public interface IAuthService {
    public boolean authenticate(String username, String password);
    public String determineRedirectUrl(Authentication authentication);
}
