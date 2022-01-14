package com.example.demo.queries;

public class ProductQueries {
    public final static String GET_PRODUCTS = "SELECT * FROM products";
    public final static String GET_PRODUCTS_BY_CATEGORY_ID = "SELECT * FROM products where categoryId = ?";
    public final static String GET_PRODUCT_BY_ID = "SELECT * FROM products WHERE id = ?";
    public final static String GET_PRODUCT_BY_NAME = "SELECT * FROM products WHERE name LIKE ?";

    public final static String ADD_PRODUCT = "INSERT INTO products(id, name, description, categoryId, unitPrice, unitsInStock) VALUES (?, ?, ?, ?, ?, ?)";

    public final static String UPDATE_PRODUCT = "UPDATE products SET name = ?, description = ?, categoryId = ?, unitPrice = ?, unitsInStock = ? WHERE id = ?";

    public final static String DELETE_PRODUCT = "DELETE from products WHERE id = ?";
}
