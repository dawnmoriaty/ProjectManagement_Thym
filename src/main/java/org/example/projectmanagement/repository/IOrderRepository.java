package org.example.projectmanagement.repository;

import org.example.projectmanagement.model.entity.Order;
import org.example.projectmanagement.model.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IOrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUsers(Users users);
}
