package org.example.projectmanagement.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.projectmanagement.enums.RoleUserName;
import org.example.projectmanagement.model.dtos.request.RegisterRequestDTO;
import org.example.projectmanagement.model.entity.Users;
import org.example.projectmanagement.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/super-admin")
@RequiredArgsConstructor
@Slf4j
public class SuperAdminController {
    private final IUserService userService;
    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard-super-admin";
    }
    @GetMapping("/users")
    public String listUsers(Model model) {
        List<Users> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "super-admin-user-list";
    }

    @GetMapping("/users/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new RegisterRequestDTO());
        return "register";
    }
    @PostMapping("/users/register")
    public String registerUser (@Valid @ModelAttribute("user") RegisterRequestDTO requestDTO,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "register";
        }
        try {
            userService.registerUser (requestDTO);
            redirectAttributes.addFlashAttribute("success", "Đăng ký người dùng thành công!");
            return "redirect:/super-admin/users";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "register";
        }
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser (@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            userService.deleteUser (id);
            redirectAttributes.addFlashAttribute("success", "Xóa người dùng thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/super-admin/users";
    }
    @PostMapping("/users/{userId}/roles")
    public String addRoleToUser (@PathVariable Long userId, @RequestParam RoleUserName roleName,
                                 RedirectAttributes redirectAttributes) {
        try {
            userService.addRoleToUser (userId, roleName);
            redirectAttributes.addFlashAttribute("success", "Thêm vai trò thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/super-admin/users";
    }
}