package org.example.projectmanagement.repository;

import org.example.projectmanagement.model.entity.Vehicles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IVehiclesRepository extends JpaRepository<Vehicles,Long>{
}
