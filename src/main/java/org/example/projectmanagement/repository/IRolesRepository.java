package org.example.projectmanagement.repository;

import org.example.projectmanagement.model.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRolesRepository extends JpaRepository<Roles, Integer> {
}
