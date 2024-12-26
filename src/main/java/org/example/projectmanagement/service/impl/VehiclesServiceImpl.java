package org.example.projectmanagement.service.impl;
import lombok.RequiredArgsConstructor;
import org.example.projectmanagement.model.dtos.request.VehicleRequestDTO;
import org.example.projectmanagement.model.entity.Categories;
import org.example.projectmanagement.model.entity.Vehicles;
import org.example.projectmanagement.repository.IVehiclesRepository;
import org.example.projectmanagement.service.ICategoriesService;
import org.example.projectmanagement.service.IFileService;
import org.example.projectmanagement.service.IVehiclesService;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
@RequiredArgsConstructor
public class VehiclesServiceImpl implements IVehiclesService {
    private final IVehiclesRepository vehiclesRepository;
    private final IFileService fileService;
    private final ICategoriesService categoriesService;
    @Override
    public List<Vehicles> getAllVehicles() {
        return vehiclesRepository.findAll();
    }
    @Override
    public Vehicles getVehicleById(Long id) {
        return vehiclesRepository.findById(id).orElse(null);
    }
    @Override
    public Vehicles createVehicle(VehicleRequestDTO vehicleRequestDTO) {
        String image = fileService.uploadImage(vehicleRequestDTO.getImageVehicle());
        Categories categories = categoriesService.getCategoryById(vehicleRequestDTO.getCategoryID());
        Vehicles vehicles = Vehicles.builder()
                .name(vehicleRequestDTO.getName())
                .licensePlate(vehicleRequestDTO.getLicensePlate())
                .description(vehicleRequestDTO.getDescription())
                .manufacturer(vehicleRequestDTO.getManufacturer())
                .status(vehicleRequestDTO.getStatus())
                .model(vehicleRequestDTO.getModel())
                .price(vehicleRequestDTO.getPrice())
                .categories(categories)
                .imageVehicle(image)
                .build();
        return vehiclesRepository.save(vehicles);
    }
    @Override
    public Vehicles updateVehicle(Long id, VehicleRequestDTO vehicleRequestDTO) {
        Categories categories = categoriesService.getCategoryById(vehicleRequestDTO.getCategoryID());
        Vehicles existingVehicle = vehiclesRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid vehicle Id: " + id));
        String image = (vehicleRequestDTO.getImageVehicle() != null && !vehicleRequestDTO.getImageVehicle().isEmpty())
                ? fileService.uploadImage(vehicleRequestDTO.getImageVehicle())
                : existingVehicle.getImageVehicle();
        existingVehicle.setName(vehicleRequestDTO.getName());
        existingVehicle.setLicensePlate(vehicleRequestDTO.getLicensePlate());
        existingVehicle.setDescription(vehicleRequestDTO.getDescription());
        existingVehicle.setManufacturer(vehicleRequestDTO.getManufacturer());
        existingVehicle.setModel(vehicleRequestDTO.getModel());
        existingVehicle.setStatus(vehicleRequestDTO.getStatus());
        existingVehicle.setPrice(vehicleRequestDTO.getPrice());
        existingVehicle.setCategories(categories);
        existingVehicle.setImageVehicle(image);
        return vehiclesRepository.save(existingVehicle);
    }
    @Override
    public void deleteVehicle(Long id) {
        vehiclesRepository.deleteById(id);
    }
}
