package org.example.projectmanagement.repository;

import org.example.projectmanagement.model.entity.Rentals;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IRentalsRepository extends JpaRepository<Rentals,Long> {
    List<Rentals> findAllByUser_Username(String username);
    List<Rentals> findAllByVehicle_Id(Long vehicleId);
}
