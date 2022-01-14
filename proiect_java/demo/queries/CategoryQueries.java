package com.example.demo.queries;

public class CategoryQueries {
    public final static String GET_CATEGORIES = "SELECT * FROM categories";
    public final static String GET_CATEGORY_BY_ID = "SELECT * FROM categories WHERE id = ?";
    public final static String GET_CATEGORY_BY_NAME = "SELECT * FROM categories WHERE name LIKE ?";

    public final static String ADD_CATEGORY = "INSERT INTO categories(id, name, description) VALUES (?, ?, ?)";

    public final static String UPDATE_CATEGORY = "UPDATE categories SET name = ?, description = ? WHERE id = ?";

    public final static String DELETE_CATEGORY = "DELETE from categories WHERE id = ?";
}
