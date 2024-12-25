package org.example.projectmanagement.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.projectmanagement.model.entity.Categories;
import org.example.projectmanagement.repository.ICategoriesRepository;
import org.example.projectmanagement.service.ICategoriesService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoriesServiceImpl implements ICategoriesService {
    private final ICategoriesRepository categoriesRepository;
    @Override
    public Categories createCategory(Categories category) {
        return categoriesRepository.save(category);
    }
    @Override
    public List<Categories> getAllCategories() {
        return categoriesRepository.findAll();
    }
    @Override
    public Categories getCategoryById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Category ID must not be null");
        }
        return categoriesRepository.findById(id).orElse(null);
    }
    @Override
    public Categories updateCategory(Long id, Categories categoryDetails) {
        Categories category = categoriesRepository.findById(id).orElseThrow();
        category.setNameCategory(categoryDetails.getNameCategory());
        return categoriesRepository.save(category);
    }
    @Override
    public void deleteCategory(Long id) {
        categoriesRepository.deleteById(id);
    }
}
