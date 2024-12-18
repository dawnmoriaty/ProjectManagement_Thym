package org.example.projectmanagement.controller;

import lombok.RequiredArgsConstructor;


import lombok.extern.slf4j.Slf4j;
import org.example.projectmanagement.model.dtos.request.RegisterRequestDTO;
import org.example.projectmanagement.service.IUserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final IUserService userService;
    private final AuthenticationManager authenticationManager;
    @GetMapping("/login-success")
    public String loginSuccess() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String role = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElse("");
        return switch (role) {
            case "ROLE_SUPER_ADMIN" -> "redirect:/super-admin/dashboard";
            case "ROLE_ADMIN" -> "redirect:/admin/dashboard";
            case "ROLE_EMPLOYEE" -> "redirect:/employee/home";
            case "ROLE_CUSTOMER" -> "redirect:/customer/home";
            default -> "redirect:/home";
        };
    }
    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", "Tên đăng nhập hoặc mật khẩu không chính xác!");
        }
        return "/login";
    }

    @PostMapping("/login")
    public String handleLoginPost(@RequestParam("userName") String username,
                                  @RequestParam("password") String password,
                                  Model model) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String role = authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .findFirst()
                    .orElse("");
            return switch (role) {
                case "ROLE_SUPER_ADMIN" -> "redirect:/super-admin/dashboard";
                case "ROLE_ADMIN" -> "redirect:/admin/dashboard";
                case "ROLE_EMPLOYEE" -> "redirect:/employee/home";
                case "ROLE_CUSTOMER" -> "redirect:/customer/home";
                default -> "redirect:/home";
            };
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Tên đăng nhập hoặc mật khẩu không đúng");
            return "login";
        }
    }


    // Trang đăng ký
    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("registerRequest", new RegisterRequestDTO());
        return "/register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("registerRequest") RegisterRequestDTO registerRequest, Model model) {
        try {
            userService.registerUser(registerRequest);
            return "redirect:/auth/login?success";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "/register";
        }
    }
}
