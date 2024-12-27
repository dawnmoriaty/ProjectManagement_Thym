package org.example.projectmanagement.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.projectmanagement.model.dtos.request.RentalCalculationDTO;
import org.example.projectmanagement.model.entity.Rentals;
import org.example.projectmanagement.model.entity.Users;
import org.example.projectmanagement.model.entity.Vehicles;
import org.example.projectmanagement.repository.IRentalsRepository;
import org.example.projectmanagement.repository.IUsersRepository;
import org.example.projectmanagement.repository.IVehiclesRepository;
import org.example.projectmanagement.service.IRentalsService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RentalsServiceImpl implements IRentalsService {


    private final IRentalsRepository rentalsRepository;
    private final IVehiclesRepository vehiclesRepository;
    private final IUsersRepository usersRepository;

    @Override
    public void createRental(RentalCalculationDTO rentalCalculationDTO, String username) {
        Users user = usersRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Vehicles vehicle = vehiclesRepository.findById(rentalCalculationDTO.getVehicleId())
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));
        Rentals rental = Rentals.builder()
                .user(user)
                .vehicle(vehicle)
                .rentalDate(rentalCalculationDTO.getRentalDate())
                .returnDate(rentalCalculationDTO.getReturnDate())
                .rentalPrice(rentalCalculationDTO.getTotalRental())
                .deposit(rentalCalculationDTO.getDepositAmount())
                .status("Pending")
                .note("New rental created")
                .build();

        rentalsRepository.save(rental);
    }

    @Override
    public Rentals getRentalById(Long id) {
        return rentalsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rental not found"));
    }

    @Override
    public List<Rentals> getAllRentals() {
        return rentalsRepository.findAll();
    }

    @Override
    public List<Rentals> getAllRentalsByUser(String username) {
        return rentalsRepository.findAllByUser_Username(username);
    }

    @Override
    public List<Rentals> getAllRentalsByVehicle(Long vehicleId) {
        return rentalsRepository.findAllByVehicle_Id(vehicleId);
    }

    @Override
    public void updateRentalStatus(Long rentalId, String status) {
        Rentals rental = getRentalById(rentalId);
        rental.setStatus(status);
        rentalsRepository.save(rental);
    }

    @Override
    public void deleteRental(Long rentalId) {
        rentalsRepository.deleteById(rentalId);
    }
}
