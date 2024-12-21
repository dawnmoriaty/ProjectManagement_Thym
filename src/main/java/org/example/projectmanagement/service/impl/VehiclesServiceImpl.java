package org.example.projectmanagement.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.projectmanagement.model.dtos.request.VehicleRequestDTO;
import org.example.projectmanagement.model.entity.Vehicles;
import org.example.projectmanagement.repository.IVehiclesRepository;
import org.example.projectmanagement.service.IFileService;
import org.example.projectmanagement.service.IVehiclesService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class VehiclesServiceImpl implements IVehiclesService {
    private final IVehiclesRepository vehiclesRepository;
    private final IFileService fileService;

    @Override
    public List<Vehicles> getAllVehicles() {
        return vehiclesRepository.findAll();
    }

    @Override
    public Optional<Vehicles> getVehicleById(Long id) {
        return vehiclesRepository.findById(id);
    }

    @Override
    public Vehicles createVehicle(VehicleRequestDTO vehicleRequestDTO) {
        String image = fileService.uploadImage(vehicleRequestDTO.getImageVehicle());
        Vehicles vehicles = Vehicles.builder()
                .name(vehicleRequestDTO.getName())
                .licensePlate(vehicleRequestDTO.getLicensePlate())
                .description(vehicleRequestDTO.getDescription())
                .manufacturer(vehicleRequestDTO.getManufacturer())
                .model(vehicleRequestDTO.getModel())
                .categories(vehicleRequestDTO.getCategories())
                .imageVehicle(image)
                .build();

        return vehiclesRepository.save(vehicles);
    }

    @Override
    public Vehicles updateVehicle(Long id, VehicleRequestDTO vehicleRequestDTO) {
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
        existingVehicle.setCategories(vehicleRequestDTO.getCategories());
        existingVehicle.setImageVehicle(image);
        return vehiclesRepository.save(existingVehicle);
    }


    @Override
    public void deleteVehicle(Long id) {
        vehiclesRepository.deleteById(id);
    }
}
