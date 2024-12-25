package org.example.projectmanagement.service;

import org.example.projectmanagement.model.dtos.request.VehicleRequestDTO;
import org.example.projectmanagement.model.entity.Vehicles;

import java.util.List;

public interface IVehiclesService {
    List<Vehicles> getAllVehicles();
    Vehicles getVehicleById(Long id);
    Vehicles createVehicle(VehicleRequestDTO vehicleRequestDTO);
    Vehicles updateVehicle(Long id, VehicleRequestDTO vehicleRequestDTO);
    void deleteVehicle(Long id);
}
