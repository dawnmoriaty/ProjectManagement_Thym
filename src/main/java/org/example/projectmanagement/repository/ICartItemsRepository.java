package org.example.projectmanagement.repository;

import org.example.projectmanagement.model.entity.Cart;
import org.example.projectmanagement.model.entity.CartItems;
import org.example.projectmanagement.model.entity.Vehicles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICartItemsRepository extends JpaRepository<CartItems, Long> {
    boolean existsByCartAndVehicles(Cart cart, Vehicles vehicles);
    CartItems findByCartAndVehicles(Cart cart, Vehicles vehicles);
}
