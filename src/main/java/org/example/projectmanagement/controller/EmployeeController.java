package org.example.projectmanagement.controller;

import lombok.RequiredArgsConstructor;
import org.example.projectmanagement.model.entity.Rentals;
import org.example.projectmanagement.service.IRentalsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final IRentalsService rentalsService;
    @GetMapping("/dashboard")
    public String employeeHome() {
        return "employee-home"; // Trả về template home của nhân viên
    }
    @GetMapping("/rentals")
    public String getAllRentals(Model model) {
        List<Rentals> rentals = rentalsService.getAllRentals(); // Lấy danh sách giao dịch thuê xe
        model.addAttribute("rentals", rentals);
        return "rentals-list"; // Trả về template danh sách giao dịch thuê xe
    }

    @GetMapping("/rentals/{id}")
    public String getRentalById(@PathVariable Long id, Model model) {
        Rentals rental = rentalsService.getRentalById(id); // Lấy chi tiết giao dịch thuê xe
        model.addAttribute("rental", rental);
        return "rental-detail"; // Trả về template chi tiết giao dịch thuê xe
    }

    @PutMapping("/rentals/{id}")
    public String updateRental(@PathVariable Long id, @ModelAttribute Rentals rental) {
        rentalsService.updateRental(id, rental); // Cập nhật giao dịch thuê xe
        return "redirect:/employee/rentals"; // Redirect đến danh sách giao dịch thuê xe
    }
}
