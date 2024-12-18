package org.example.projectmanagement.controller;

import lombok.RequiredArgsConstructor;
import org.example.projectmanagement.security.UserPrinciple;
import org.example.projectmanagement.service.IUserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final IUserService userService;

    // Trang dashboard của admin
    @GetMapping("/dashboard")
    public String dashboard(Model model, @AuthenticationPrincipal UserPrinciple principal) {
        if (principal.getAuthorities().stream().noneMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            return "redirect:/denied";
        }

        // Lấy danh sách người dùng
        model.addAttribute("users", userService.getAllUsers());
        return "admin/dashboard";
    }

//    // Trang thêm người dùng mới
//    @GetMapping("/users/new")
//    public String showCreateUserForm(Model model) {
//        model.addAttribute("user", new User());  // Thêm một đối tượng User mới vào model
//        return "admin/create-user";
//    }
//
//    // Xử lý tạo người dùng mới
//    @PostMapping("/users")
//    public String createUser(@ModelAttribute User user) {
//        userService.createUser(user);  // Gọi service để tạo người dùng
//        return "redirect:/admin/dashboard";
//    }
//
//    // Trang chỉnh sửa người dùng
//    @GetMapping("/users/edit/{id}")
//    public String showEditUserForm(@PathVariable("id") Long id, Model model) {
//        User user = userService.getUserById(id);  // Lấy thông tin người dùng theo ID
//        model.addAttribute("user", user);
//        return "admin/edit-user";
//    }
//
//    // Xử lý cập nhật người dùng
//    @PostMapping("/users/{id}")
//    public String updateUser(@PathVariable("id") Long id, @ModelAttribute User user) {
//        userService.updateUser(id, user);  // Cập nhật người dùng trong database
//        return "redirect:/admin/dashboard";
//    }
//
//    // Xử lý xóa người dùng
//    @PostMapping("/users/delete/{id}")
//    public String deleteUser(@PathVariable("id") Long id) {
//        userService.deleteUser(id);  // Gọi service để xóa người dùng
//        return "redirect:/admin/dashboard";
//    }
}
