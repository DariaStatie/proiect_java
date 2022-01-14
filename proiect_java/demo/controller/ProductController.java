package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/get")
    public ResponseEntity<List<Product>> getProducts() {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProducts());
    }

    @GetMapping("/get/category/{categoryId}")
    public ResponseEntity<?> getProducts(@PathVariable int categoryId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(productService.getProductsByCategory(categoryId));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("get/id/{id}")
    public ResponseEntity<?> getProduct(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(productService.getProductById(id));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/get/name/{name}")
    public ResponseEntity<?> getProduct(@PathVariable String name) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(productService.getProductByName(name));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Product> addProduct(@RequestBody @Valid Product product) {
        Product createdProduct = productService.addProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    @PutMapping("/edit")
    public ResponseEntity<?> editProduct(@RequestBody @Valid Product newProduct) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(productService.editProduct(newProduct));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(productService.deleteProduct(id));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
