package org.example.projectmanagement.controller;
import lombok.RequiredArgsConstructor;
import org.example.projectmanagement.model.dtos.request.VehicleRequestDTO;
import org.example.projectmanagement.model.entity.Categories;
import org.example.projectmanagement.model.entity.Rentals;
import org.example.projectmanagement.model.entity.Vehicles;
import org.example.projectmanagement.repository.ICategoriesRepository;
import org.example.projectmanagement.service.ICategoriesService;
import org.example.projectmanagement.service.IRentalsService;
import org.example.projectmanagement.service.IVehiclesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final ICategoriesService categoriesService;
    private final IVehiclesService vehiclesService;
    private final ICategoriesRepository categoriesRepository;
    private final IRentalsService rentalsService;

    // =============================DASHBOARD==================================
    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard-admin";
    }

    // =============================CATEGORIES==================================
    @GetMapping("/categories")
    public String listCategories(Model model) {
        List<Categories> categoriesList = categoriesService.getAllCategories();
        model.addAttribute("categories", categoriesList);
        return "categories-list";
    }

    @GetMapping("/categories/new")
    public String createCategoryForm(Model model) {
        model.addAttribute("category", new Categories());
        return "category-form";
    }

    @PostMapping("/categories")
    public String createCategory(@ModelAttribute Categories category) {
        categoriesService.createCategory(category);
        return "redirect:/admin/categories";
    }

    @GetMapping("/categories/edit/{id}")
    public String editCategoryForm(@PathVariable Long id, Model model) {
        Categories category = categoriesService.getCategoryById(id);
        model.addAttribute("category", category);
        return "category-form";
    }

    @PostMapping("/categories/update/{id}")
    public String updateCategory(@PathVariable Long id, @ModelAttribute Categories categoryDetails) {
        categoriesService.updateCategory(id, categoryDetails);
        return "redirect:/admin/categories";
    }

    @GetMapping("/categories/delete/{id}")
    public String deleteCategory(@PathVariable Long id) {
        categoriesService.deleteCategory(id);
        return "redirect:/admin/categories";
    }
    // =============================VEHICLES==================================
    @GetMapping("/vehicles")
    public String listVehicles(Model model) {
        List<Vehicles> vehiclesList = vehiclesService.getAllVehicles();
        model.addAttribute("vehicles", vehiclesList);
        List<Categories> categoriesList = categoriesRepository.findAll();
        model.addAttribute("categories", categoriesList);
        List<Rentals> rentals = rentalsService.getAllRentals();
        model.addAttribute("rentals", rentals);
        long availableVehiclesCount = vehiclesList.stream()
                .filter(v -> "AVAILABLE".equals(v.getStatus()))
                .count();
        model.addAttribute("availableVehiclesCount", availableVehiclesCount);

        long totalExpectedRevenue = vehiclesList.stream()
                .mapToLong(v -> v.getPrice().longValue())
                .sum();
        model.addAttribute("totalExpectedRevenue", totalExpectedRevenue);

        model.addAttribute("totalExpectedRevenue", totalExpectedRevenue);

        return "vehicles-list";
    }

    @GetMapping("/vehicles/new")
    public String createVehicleForm(Model model) {
        model.addAttribute("vehicles", new VehicleRequestDTO());
        model.addAttribute("categories", categoriesService.getAllCategories());
        return "vehicles-form";
    }

    @PostMapping("/vehicles")
    public String createVehicle(@ModelAttribute VehicleRequestDTO vehicleRequestDTO,Model model) {
        Vehicles vehicles = vehiclesService.createVehicle(vehicleRequestDTO);
        model.addAttribute("vehicles", vehicles);
        return "redirect:/admin/vehicles";
    }

    @GetMapping("/vehicles/edit/{id}")
    public String editVehicleForm(@PathVariable Long id, Model model) {
        Vehicles vehicle = vehiclesService.getVehicleById(id);
        model.addAttribute("vehicles", vehicle);
        List<Categories> categoriesList = categoriesRepository.findAll();
        model.addAttribute("categories", categoriesList);
        return "vehicle-update";
    }

    @PostMapping("/vehicles/update/{id}")
    public String updateVehicle(@PathVariable Long id, @ModelAttribute VehicleRequestDTO vehicleRequestDTO) {
        vehiclesService.updateVehicle(id, vehicleRequestDTO);
        return "redirect:/admin/vehicles";
    }

    @GetMapping("/vehicles/delete/{id}")
    public String deleteVehicle(@PathVariable Long id) {
        vehiclesService.deleteVehicle(id);
        return "redirect:/admin/vehicles";
    }
}