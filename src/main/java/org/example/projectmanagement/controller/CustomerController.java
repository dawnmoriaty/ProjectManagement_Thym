package org.example.projectmanagement.controller;
import lombok.RequiredArgsConstructor;
import org.example.projectmanagement.model.dtos.request.RentalCalculationDTO;
import org.example.projectmanagement.service.IRentalsService;
import org.example.projectmanagement.service.IVehiclesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;

@Controller
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final IVehiclesService vehiclesService;
    private final IRentalsService rentalsService;
    @GetMapping("/dashboard")
    public String customerHome() {
        return "customer-home";
    }
    @GetMapping("/vehicles")
    public String getVehicles(Model model) {
        model.addAttribute("vehicles", vehiclesService.getAllVehicles());
        return "vehicles-user-list";
    }
    @GetMapping("/vehicles/{id}")
    public String getVehicleDetails(@PathVariable Long id, Model model) {
        model.addAttribute("vehicle", vehiclesService.getVehicleById(id));
        return "vehicle-details";
    }
    @GetMapping("/rentals/form/{id}")
    public String showRentalForm(@PathVariable Long id, Model model) {
        model.addAttribute("vehicleId", id);
        model.addAttribute("rentalCalculationDTO", new RentalCalculationDTO());
        return "rental-form";
    }
    @PostMapping("/rentals")
    public String createRental(@ModelAttribute RentalCalculationDTO rentalCalculationDTO, Principal principal) {
        rentalsService.createRental(rentalCalculationDTO, principal.getName());
        return "redirect:/customer/rentals/history";
    }
    @GetMapping("/rentals/history")
    public String getRentalHistory(Model model, Principal principal) {
        model.addAttribute("rentals", rentalsService.getAllRentalsByUser(principal.getName()));
        return "rental-history";
    }
}

