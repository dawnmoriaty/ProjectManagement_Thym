package org.example.projectmanagement.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.projectmanagement.model.entity.Rentals;
import org.example.projectmanagement.model.entity.Vehicles;
import org.example.projectmanagement.service.IRentalsService;
import org.example.projectmanagement.service.IVehiclesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final IVehiclesService vehiclesService; // Service để lấy thông tin xe
    private final IRentalsService rentalsService; // Service để quản lý giao dịch thuê xe

    @GetMapping("/dashboard")
    public String customerHome() {
        return "customer-home"; // Redirect đến trang home của khách hàng
    }

    @GetMapping("/vehicles")
    public String getAllVehicles(Model model) {
        List<Vehicles> vehicles = vehiclesService.getAllVehicles(); // Lấy danh sách xe
        model.addAttribute("vehicles", vehicles);
        return "vehicles-user-list"; // Trả về template danh sách xe

    }

    @GetMapping("/vehicles/{id}")
    public String getVehicleById(@PathVariable Long id, Model model) {
        Vehicles vehicle = vehiclesService.getVehicleById(id); // Lấy chi tiết xe
        model.addAttribute("vehicle", vehicle);
        return "vehicle-details"; // Trả về template chi tiết xe
    }

    @GetMapping("/rentals/form/{id}")
    public String showRentalForm(@PathVariable Long id, Model model) {
        Vehicles vehicle = vehiclesService.getVehicleById(id); // Lấy xe để hiển thị trong form
        model.addAttribute("vehicle", vehicle);
        return "rental-form"; // Trả về template form thuê xe
    }

    @PostMapping("/rentals")
    public String createRental(@ModelAttribute Rentals rental) {
        rentalsService.createRental(rental); // Tạo giao dịch thuê xe mới
        return "redirect:/customer/rentals/history"; // Redirect đến lịch sử thuê xe
    }

    @GetMapping("/rentals/history")
    public String getRentalHistory(HttpSession session, Model model) {
        Long userId = (Long) session.getAttribute("userId");
        List<Rentals> rentalHistory = rentalsService.getAllRentalsByUser(userId);
        model.addAttribute("rentalHistory", rentalHistory);
        return "rental-history";
    }
}

