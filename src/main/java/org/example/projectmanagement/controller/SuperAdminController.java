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
        return "dashboard-admin";
    }

    // Hiển thị danh sách người dùng
    @GetMapping("/users")
    public String listUsers(Model model) {
        List<Users> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "super-admin-user-list"; // Tên view để hiển thị danh sách người dùng
    }

    // Hiển thị trang đăng ký người dùng mới
    @GetMapping("/users/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new RegisterRequestDTO());
        return "register-user"; // Tên view để hiển thị form đăng ký
    }

    // Xử lý đăng ký người dùng mới
    @PostMapping("/users/register")
    public String registerUser (@Valid @ModelAttribute("user") RegisterRequestDTO requestDTO,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "register-user"; // Trả về form nếu có lỗi
        }
        try {
            userService.registerUser (requestDTO);
            redirectAttributes.addFlashAttribute("success", "Đăng ký người dùng thành công!");
            return "redirect:/super-admin/users";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/super-admin/users/register";
        }
    }

    // Hiển thị trang chỉnh sửa người dùng
    @GetMapping("/users/edit/{id}")
    public String showEditUserForm(@PathVariable Long id, Model model) {
        Users user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "edit-user"; // Tên view để hiển thị form chỉnh sửa
    }

    // Xử lý chỉnh sửa người dùng
    @PostMapping("/users/edit/{id}")
    public String editUser (@PathVariable Long id, @Valid @ModelAttribute("user") Users user,
                            BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "edit-user"; // Trả về form nếu có lỗi
        }
        try {
            userService.updateUser (id, user);
            redirectAttributes.addFlashAttribute("success", "Cập nhật người dùng thành công!");
            return "redirect:/super-admin/users";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/super-admin/users/edit/" + id;
        }
    }

    // Xử lý xóa người dùng
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

    // Thêm vai trò cho người dùng
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