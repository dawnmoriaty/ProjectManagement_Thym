package org.example.projectmanagement.repository;

import org.example.projectmanagement.model.entity.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoriesRepository extends JpaRepository<Categories, Long> {
}
