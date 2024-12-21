package org.example.projectmanagement.repository;

import org.example.projectmanagement.model.entity.Cart;
import org.example.projectmanagement.model.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICartRepository extends JpaRepository<Cart, Long> {
    Cart findByUsers(Users users);
}
