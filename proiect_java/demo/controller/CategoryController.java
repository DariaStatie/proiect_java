package com.example.demo.controller;

import com.example.demo.model.Category;
import com.example.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/get")
    public ResponseEntity<List<Category>> getCategories() {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getCategories());
    }

    @GetMapping("/get/id/{id}")
    public ResponseEntity<?> getCategory(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(categoryService.getCategoryById(id));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/get/name/{name}")
    public ResponseEntity<?> getCategory(@PathVariable String name) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(categoryService.getCategoryByName(name));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Category> addCategory(@RequestBody @Valid Category category) {
        Category createdCategory = categoryService.addCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCategory);
    }

    @PutMapping("/edit")
    public ResponseEntity<?> editCategory(@RequestBody @Valid Category newCategory) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(categoryService.editCategory(newCategory));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(categoryService.deleteCategory(id));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
