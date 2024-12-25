package org.example.projectmanagement.controller;
import lombok.RequiredArgsConstructor;
import org.example.projectmanagement.model.dtos.request.VehicleRequestDTO;
import org.example.projectmanagement.model.entity.Categories;
import org.example.projectmanagement.model.entity.Vehicles;
import org.example.projectmanagement.repository.ICategoriesRepository;
import org.example.projectmanagement.service.ICategoriesService;
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

    // =============================DASHBOARD==================================
    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard-admin"; // Tên view cho dashboard
    }

    // =============================CATEGORIES==================================
    @GetMapping("/categories")
    public String listCategories(Model model) {
        List<Categories> categoriesList = categoriesService.getAllCategories();
        model.addAttribute("categories", categoriesList);
        return "categories-list"; // Tên view cho danh sách danh mục
    }

    @GetMapping("/categories/new")
    public String createCategoryForm(Model model) {
        model.addAttribute("category", new Categories());
        return "category-form"; // Tên view cho form tạo mới danh mục
    }

    @PostMapping("/categories")
    public String createCategory(@ModelAttribute Categories category) {
        categoriesService.createCategory(category);
        return "redirect:/admin/categories"; // Chuyển hướng về danh sách danh mục
    }

    @GetMapping("/categories/edit/{id}")
    public String editCategoryForm(@PathVariable Long id, Model model) {
        Categories category = categoriesService.getCategoryById(id);
        model.addAttribute("category", category);
        return "category-form"; // Tên view cho form chỉnh sửa danh mục
    }

    @PostMapping("/categories/update/{id}")
    public String updateCategory(@PathVariable Long id, @ModelAttribute Categories categoryDetails) {
        categoriesService.updateCategory(id, categoryDetails);
        return "redirect:/admin/categories"; // Chuyển hướng về danh sách danh mục
    }

    @GetMapping("/categories/delete/{id}")
    public String deleteCategory(@PathVariable Long id) {
        categoriesService.deleteCategory(id);
        return "redirect:/admin/categories"; // Chuyển hướng về danh sách danh mục
    }
    // =============================VEHICLES==================================
    @GetMapping("/vehicles")
    public String listVehicles(Model model) {
        List<Vehicles> vehiclesList = vehiclesService.getAllVehicles();
        model.addAttribute("vehicles", vehiclesList);
        List<Categories> categoriesList = categoriesRepository.findAll();
        model.addAttribute("categories", categoriesList);
        return "vehicles-list";
    }

    @GetMapping("/vehicles/new")
    public String createVehicleForm(Model model) {
        model.addAttribute("vehicles", new VehicleRequestDTO());
        model.addAttribute("categories", categoriesService.getAllCategories()); // Thêm danh sách danh mục cho dropdown
        return "vehicles-form"; // Tên view cho form tạo mới xe
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
        model.addAttribute("categories", categoriesList); // Thêm danh sách danh mục cho dropdown
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