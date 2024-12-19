package org.example.projectmanagement.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        String redirectUrl = determineTargetUrl(authentication);
        response.sendRedirect(redirectUrl);
    }

    protected String determineTargetUrl(Authentication authentication) {
        // Logic để xác định URL đích dựa trên vai trò của người dùng
        if (authentication.getAuthorities().stream().anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"))) {
            return "/admin/dashboard"; // Redirect đến trang admin
        } else if (authentication.getAuthorities().stream().anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_SUPER_ADMIN"))) {
            return "/super-admin/dashboard"; // Redirect đến trang super admin
        } else if (authentication.getAuthorities().stream().anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_EMPLOYEE"))) {
            return "/employee/dashboard"; // Redirect đến trang employee
        } else if (authentication.getAuthorities().stream().anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_CUSTOMER"))) {
            return "/customer/dashboard"; // Redirect đến trang customer
        }

        return "/home"; // Redirect đến trang mặc định
    }
}