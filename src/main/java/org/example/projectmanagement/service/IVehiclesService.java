package org.example.projectmanagement.service;

import org.example.projectmanagement.model.dtos.request.VehicleRequestDTO;
import org.example.projectmanagement.model.entity.Vehicles;

import java.util.List;
import java.util.Optional;

public interface IVehiclesService {
    List<Vehicles> getAllVehicles();
    Optional<Vehicles> getVehicleById(Long id);
    Vehicles createVehicle(VehicleRequestDTO vehicleRequestDTO);
    Vehicles updateVehicle(Long id, VehicleRequestDTO vehicleRequestDTO);
    void deleteVehicle(Long id);
}
