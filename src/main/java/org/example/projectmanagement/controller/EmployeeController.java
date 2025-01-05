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
    @GetMapping("/dashboard")
    public String employeeHome() {
        return "employee-home";
    }

    // Hiển thị danh sách giao dịch thuê xe
    @GetMapping("/rentals")
    public String getAllRentals(Model model) {
        model.addAttribute("rentals", rentalsService.getAllRentals());
        return "rentals-list";
    }

    @GetMapping("/rentals/{id}")
    public String getRentalDetails(@PathVariable Long id, Model model) {
        model.addAttribute("rental", rentalsService.getRentalById(id));
        return "rental-detail";
    }

    @PostMapping("/rentals/{id}")
    public String updateRental(@PathVariable Long id, @RequestParam String status) {
        rentalsService.updateRentalStatus(id, status);
        return "redirect:/employee/rentals";
    }
}
