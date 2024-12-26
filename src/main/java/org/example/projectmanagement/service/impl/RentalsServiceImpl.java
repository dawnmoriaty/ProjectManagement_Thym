package org.example.projectmanagement.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.projectmanagement.model.entity.Rentals;
import org.example.projectmanagement.model.entity.Users;
import org.example.projectmanagement.model.entity.Vehicles;
import org.example.projectmanagement.repository.IRentalsRepository;
import org.example.projectmanagement.repository.IUsersRepository;
import org.example.projectmanagement.repository.IVehiclesRepository;
import org.example.projectmanagement.service.IRentalsService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RentalsServiceImpl implements IRentalsService {
    private final IRentalsRepository rentalsRepository;
    private final IUsersRepository usersRepository;
    private final IVehiclesRepository vehiclesRepository;

    @Override
    @Transactional
    public Rentals createRental(Rentals rental) {
        // Kiểm tra đầu vào
        if (rental == null) {
            throw new IllegalArgumentException("Rental cannot be null");
        }

        // Kiểm tra thông tin xe
        if (rental.getVehicle() == null || rental.getVehicle().getId() == null) {
            throw new IllegalArgumentException("Vehicle information is required");
        }

        // Kiểm tra thông tin người dùng
        if (rental.getUser() == null || rental.getUser().getId() == null) {
            throw new IllegalArgumentException("User information is required");
        }

        // Tìm và kiểm tra xe
        Vehicles vehicle = vehiclesRepository.findById(rental.getVehicle().getId())
                .orElseThrow(() -> new RuntimeException("Vehicle not found with ID: " + rental.getVehicle().getId()));

        // Tìm và kiểm tra người dùng
        Users user = usersRepository.findById(rental.getUser().getId())
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + rental.getUser().getId()));

        // Kiểm tra trạng thái xe
        if (!"available".equalsIgnoreCase(vehicle.getStatus())) {
            throw new RuntimeException("Vehicle is not available for rental");
        }

        // Tạo rental mới
        Rentals newRental = new Rentals();

        // Set thông tin cơ bản
        newRental.setUser(user);
        newRental.setVehicle(vehicle);
        newRental.setRentalDate(new Date());
        newRental.setStatus("ongoing");

        // Sao chép các thuộc tính từ rental gốc (nếu cần)
        newRental.setReturnDate(rental.getReturnDate());
        newRental.setNote(rental.getNote());

        // Cập nhật trạng thái xe
        vehicle.setStatus("rented");
        vehiclesRepository.save(vehicle);

        // Lưu và trả về rental
        return rentalsRepository.save(newRental);
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
    public List<Rentals> getAllRentalsByUser(Long userId) {
        return rentalsRepository.findByUserId(userId);
    }

    @Override
    public List<Rentals> getAllRentalsByVehicle(Long vehicleId) {
        return rentalsRepository.findByVehicleId(vehicleId);
    }

    @Override
    @Transactional
    public Rentals updateRental(Long id, Rentals rental) {
        Rentals existingRental = getRentalById(id);
        existingRental.setReturnDate(rental.getReturnDate());
        existingRental.setStatus(rental.getStatus());
        existingRental.setNote(rental.getNote());
        return rentalsRepository.save(existingRental);
    }

    @Override
    @Transactional
    public void deleteRental(Long id) {
        Rentals rental = getRentalById(id);
        rentalsRepository.delete(rental);
    }
}
