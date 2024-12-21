package org.example.projectmanagement.repository;

import org.example.projectmanagement.model.entity.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ICategoriesRepository extends JpaRepository<Categories, Long> {
    Optional<Categories> findByNameCategoryContaining(String name);
}
