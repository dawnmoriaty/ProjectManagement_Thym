package org.example.projectmanagement.service;

import org.example.projectmanagement.model.entity.Rentals;

import java.util.List;

public interface IRentalsService {
    Rentals createRental(Rentals rental);
    Rentals getRentalById(Long id);
    List<Rentals> getAllRentals();
    List<Rentals> getAllRentalsByUser (Long userId);
    List<Rentals> getAllRentalsByVehicle(Long vehicleId);
    Rentals updateRental(Long id, Rentals rental);
    void deleteRental(Long id);
}
