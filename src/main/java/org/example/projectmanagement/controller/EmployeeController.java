package org.example.projectmanagement.controller;

import lombok.RequiredArgsConstructor;
import org.example.projectmanagement.service.IRentalsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final IRentalsService rentalsService;

    // Trang tổng quan cho nhân viên
    @GetMapping("/dashboard")
    public String employeeHome() {
        return "employee-home"; // Tên template trang tổng quan cho nhân viên
    }

    // Hiển thị danh sách giao dịch thuê xe
    @GetMapping("/rentals")
    public String getAllRentals(Model model) {
        model.addAttribute("rentals", rentalsService.getAllRentals());
        return "rentals-list"; // Tên template hiển thị danh sách giao dịch thuê xe
    }

    // Hiển thị chi tiết giao dịch thuê xe
    @GetMapping("/rentals/{id}")
    public String getRentalDetails(@PathVariable Long id, Model model) {
        model.addAttribute("rental", rentalsService.getRentalById(id));
        return "rental-detail"; // Tên template hiển thị chi tiết giao dịch thuê xe
    }

    // Cập nhật trạng thái giao dịch thuê xe (VD: xác nhận trả xe)
    @PostMapping("/rentals/{id}")
    public String updateRental(@PathVariable Long id, @RequestParam String status) {
        rentalsService.updateRentalStatus(id, status);
        return "redirect:/employee/rentals";
    }
}
