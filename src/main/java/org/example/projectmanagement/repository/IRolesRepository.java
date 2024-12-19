package org.example.projectmanagement.repository;

import org.example.projectmanagement.enums.RoleUserName;
import org.example.projectmanagement.model.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRolesRepository extends JpaRepository<Roles, Integer> {
    Optional<Object> findByRoleName(RoleUserName roleUserName);
}
