package com.example.demo.service;

import com.example.demo.exceptions.ObjectNotFoundException;
import com.example.demo.model.Category;
import com.example.demo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getCategories() {
        return categoryRepository.getCategories();
    }

    public Optional<Category> getCategoryById(int id) {
        Optional<Category> existingCategory = categoryRepository.getCategoryById(id);

        if (existingCategory.isEmpty()) {
            throw new ObjectNotFoundException("Nu exista categoria cu id-ul specificat!");
        }

        return existingCategory;
    }

    public Optional<Category> getCategoryByName(String name) {
        Optional<Category> existingCategory = categoryRepository.getCategoryByName(name);

        if (existingCategory.isEmpty()) {
            throw new ObjectNotFoundException("Nu exista categoria cu numele specificat!");
        }

        return existingCategory;
    }

    public Category addCategory(Category category) {
        return categoryRepository.addCategory(category);
    }

    public Category editCategory(Category newCategory) {
        Optional<Category> existingCategory = categoryRepository.getCategoryById(newCategory.getId());

        if (existingCategory.isEmpty()) {
            throw new ObjectNotFoundException("Nu exista categoria cu id-ul sepcificat!");
        }

        return categoryRepository.editCategory(newCategory);
    }

    public Optional<Category> deleteCategory(int id) {
        Optional<Category> category = categoryRepository.getCategoryById(id);

        if (category.isEmpty()) {
            throw new ObjectNotFoundException("Nu exista categoria cu id-ul sepcificat!");
        }

        return categoryRepository.deleteCategory(id);
    }
}
