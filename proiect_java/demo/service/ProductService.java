package com.example.demo.service;

import com.example.demo.exceptions.ObjectNotFoundException;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public List<Product> getProducts() {
        return productRepository.getProducts();
    }

    public Optional<List<Product>> getProductsByCategory(int categoryId) {
        Optional<List<Product>> products = productRepository.getProductsByCategory(categoryId);

        if (products.isEmpty()) {
            throw new ObjectNotFoundException("Nu exista produse in categoria specificata!");
        }

        return products;
    }

    public Optional<Product> getProductById(int id) {
        Optional<Product> existingProduct = productRepository.getProductById(id);

        if (existingProduct.isEmpty()) {
            throw new ObjectNotFoundException("Nu exista produsul cu id-ul sepcificat!");
        }

        return existingProduct;
    }

    public Optional<Product> getProductByName(String name) {
        Optional<Product> existingProduct = productRepository.getProductByName(name);

        if (existingProduct.isEmpty()) {
            throw new ObjectNotFoundException("Nu exista produsul cu id-ul sepcificat!");
        }

        return existingProduct;
    }

    public Product addProduct(Product product) {
        return productRepository.addProduct(product);
    }

    public Product editProduct(Product newProduct) {
        Optional<Product> existingProduct = productRepository.getProductById(newProduct.getId());

        if (existingProduct.isEmpty()) {
            throw new ObjectNotFoundException("Nu exista produsul cu id-ul specificat!");
        }

        return productRepository.editProduct(newProduct);
    }

    public Optional<Product> deleteProduct(int id) {
        Optional<Product> existingProduct = productRepository.getProductById(id);

        if (existingProduct.isEmpty()) {
            throw new ObjectNotFoundException("Nu exista produsul cu id-ul specificat!");
        }

        return productRepository.deleteProduct(id);
    }
}
