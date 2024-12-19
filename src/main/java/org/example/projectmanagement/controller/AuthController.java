package org.example.projectmanagement.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.projectmanagement.model.dtos.request.LoginRequestDTO;
import org.example.projectmanagement.model.dtos.request.RegisterRequestDTO;
import org.example.projectmanagement.service.IAuthService;
import org.example.projectmanagement.service.IUserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final IUserService userService;
    private final IAuthService authService;

    // Trang login với model
    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("loginRequest", new LoginRequestDTO());
        return "/login";
    }

    // Xử lý login
    @PostMapping("/login")
    public String login(
            @Valid @ModelAttribute("loginRequest") LoginRequestDTO loginDTO,
            BindingResult bindingResult,
            HttpServletRequest request,
            RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            return "/login";
        }

        try {
            boolean isAuthenticated = authService.authenticate(
                    loginDTO.getUsername(),
                    loginDTO.getPassword()
            );
            if (isAuthenticated) {
                Authentication authentication =
                        SecurityContextHolder.getContext().getAuthentication();
                String redirectUrl = authService.determineRedirectUrl(authentication);

                return "redirect:" + redirectUrl;
            } else {
                redirectAttributes.addFlashAttribute(
                        "error",
                        "Tên đăng nhập hoặc mật khẩu không chính xác"
                );
                return "redirect:/auth/login";
            }
        } catch (Exception e) {
            log.error("Lỗi đăng nhập", e);
            redirectAttributes.addFlashAttribute(
                    "error",
                    "Có lỗi xảy ra trong quá trình đăng nhập"
            );
            return "redirect:/auth/login";
        }
    }
    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new RegisterRequestDTO());
        return "/register";
    }

    @PostMapping("/register")
    public String register(
            @Valid @ModelAttribute("user") RegisterRequestDTO dto,
            BindingResult result,
            RedirectAttributes redirect
    ) {
        if (result.hasErrors()) {
            return "/register";
        }
        try {
            userService.registerUser(dto);
            redirect.addFlashAttribute("success", "Đăng ký thành công!");
            return "redirect:/auth/login";
        } catch (Exception e) {
            redirect.addFlashAttribute("error", e.getMessage());
            return "redirect:/auth/register";
        }
    }

    // Logout endpoint
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        SecurityContextHolder.clearContext();
        return "redirect:/auth/login?logout=true";
    }
}
