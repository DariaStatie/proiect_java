package com.example.demo.repository;

import com.example.demo.model.Product;
import com.example.demo.queries.ProductQueries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final Logger logger = LoggerFactory.getLogger(ProductRepository.class);

    public List<Product> getProducts() {
        List<Product> products = jdbcTemplate.query(ProductQueries.GET_PRODUCTS, new ProductsRowMapper());
        logger.info("Au fost returnate produsele {}", products);
        return products;
    }

    public Optional<List<Product>> getProductsByCategory(int categoryId) {
        List<Product> products = jdbcTemplate.query(ProductQueries.GET_PRODUCTS_BY_CATEGORY_ID, new ProductsRowMapper(), categoryId);
        if (!products.isEmpty()) {
            return Optional.of(products);
        }
        return Optional.empty();
    }

    public Optional<Product> getProductById(int id) {
        List<Product> products = jdbcTemplate.query(ProductQueries.GET_PRODUCT_BY_ID, new ProductsRowMapper(), id);
        Optional<Product> product = getProductFromResults(products);
        logger.info("A fost returnat produsul {}", product);
        return product;
    }

    public Optional<Product> getProductByName(String name) {
        List<Product> products = jdbcTemplate.query(ProductQueries.GET_PRODUCT_BY_NAME, new ProductsRowMapper(), name);
        Optional<Product> product = getProductFromResults(products);
        logger.info("A fost returnat produsul {}", product);
        return product;
    }

    public Product addProduct(Product product) {
        PreparedStatementCreator preparedStatementCreator = (connection) -> {
            PreparedStatement preparedStatement = connection.prepareStatement(ProductQueries.ADD_PRODUCT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1, null);
            preparedStatement.setString(2, product.getName());
            preparedStatement.setString(3, product.getDescription());
            preparedStatement.setInt(4, product.getCategoryId());
            preparedStatement.setFloat(5, product.getUnitPrice());
            preparedStatement.setInt(6, product.getUnitsInStock());
            return preparedStatement;
        };

        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(preparedStatementCreator, generatedKeyHolder);
        product.setId(generatedKeyHolder.getKey().intValue());
        logger.info("A fost adaugat produsul {}", product);
        return product;
    }

    public Product editProduct(Product newProduct) {
        jdbcTemplate.update(ProductQueries.UPDATE_PRODUCT, newProduct.getName(), newProduct.getDescription(), newProduct.getCategoryId(),
                newProduct.getUnitPrice(), newProduct.getUnitsInStock(), newProduct.getId());
        logger.info("A fost editat produsul {}", newProduct);
        return newProduct;
    }

    public Optional<Product> deleteProduct(int id) {
        Optional<Product> product = getProductById(id);
        jdbcTemplate.update(ProductQueries.DELETE_PRODUCT, id);
        logger.info("A fost sters produsul {}", product);
        return product;
    }

    // utils
    private Optional<Product> getProductFromResults(List<Product> products) {
        if (products != null && !products.isEmpty()) {
            return Optional.of(products.get(0));
        }
        return Optional.empty();
    }

    public static class ProductsRowMapper implements RowMapper<Product> {

        @Override
        public Product mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Product(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("description"),
                    resultSet.getInt("categoryId"),
                    resultSet.getInt("unitPrice"),
                    resultSet.getInt("unitsInStock")
            );
        }
    }
}
