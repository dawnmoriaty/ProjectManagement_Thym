package org.example.projectmanagement.service;
import org.example.projectmanagement.model.dtos.request.RentalCalculationDTO;
import org.example.projectmanagement.model.entity.Rentals;
import java.util.List;

public interface IRentalsService {
    void createRental(RentalCalculationDTO rentalCalculationDTO, String username);
    Rentals getRentalById(Long id);
    List<Rentals> getAllRentals();
    List<Rentals> getAllRentalsByUser(String username);
    List<Rentals> getAllRentalsByVehicle(Long vehicleId);
    void updateRentalStatus(Long rentalId, String status);
    void deleteRental(Long rentalId);
}
