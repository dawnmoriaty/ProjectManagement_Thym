package org.example.projectmanagement.controller;

import lombok.RequiredArgsConstructor;
import org.example.projectmanagement.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final IUserService userService;
    // Trang đăng nhập
    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", "Sai tên đăng nhập hoặc mật khẩu!");
        }
        return "login";
    }

    // Trang đăng ký
    @GetMapping("/register")
    public String showRegisterForm() {
        return "register";
    }

    // Xử lý đăng ký
    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password, Model model) {
        try {
            userService.register(username, password);  // Gọi service để đăng ký
            return "redirect:/auth/login";  // Sau khi đăng ký thành công, chuyển hướng đến trang đăng nhập
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());  // Trả về lỗi nếu tên đăng nhập đã tồn tại
            return "register";  // Hiển thị lại trang đăng ký
        }
    }
}
