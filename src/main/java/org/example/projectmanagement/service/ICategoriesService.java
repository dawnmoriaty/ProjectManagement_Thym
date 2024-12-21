package org.example.projectmanagement.service;

import org.example.projectmanagement.model.entity.Categories;

import java.util.List;
import java.util.Optional;

public interface ICategoriesService {
    public Categories createCategory(Categories category);
    public List<Categories> getAllCategories();
    public Optional<Categories> getCategoryById(Long id);
    public Categories updateCategory(Long id, Categories categoryDetails);
    public void deleteCategory(Long id);
}
