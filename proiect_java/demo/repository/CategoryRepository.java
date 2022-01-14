package com.example.demo.repository;

import com.example.demo.model.Category;
import com.example.demo.queries.CategoryQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
public class CategoryRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final Logger logger = LoggerFactory.getLogger(CategoryRepository.class);

    public List<Category> getCategories() {
        List<Category> categories = jdbcTemplate.query(CategoryQueries.GET_CATEGORIES, new CategoriesRowMapper());
        logger.info("Au fost returnate categoriile {}", categories);
        return categories;
    }


    public Optional<Category> getCategoryById(int id) {
        List<Category> categories = jdbcTemplate.query(CategoryQueries.GET_CATEGORY_BY_ID, new CategoriesRowMapper(), id);
        Optional<Category> category = getCategoryFromResults(categories);
        logger.info("A fost returnata categoria {}", category);
        return category;
    }

    public Optional<Category> getCategoryByName(String name) {
        List<Category> categories = jdbcTemplate.query(CategoryQueries.GET_CATEGORY_BY_NAME, new CategoriesRowMapper(), name);
        Optional<Category> category = getCategoryFromResults(categories);
        logger.info("A fost returnata categoria {}", category);
        return category;
    }

    public Category addCategory(Category category) {
        PreparedStatementCreator preparedStatementCreator = (connection) -> {
            PreparedStatement preparedStatement = connection.prepareStatement(CategoryQueries.ADD_CATEGORY, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1, null);
            preparedStatement.setString(2, category.getName());
            preparedStatement.setString(3, category.getDescription());
            return preparedStatement;
        };

        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(preparedStatementCreator, generatedKeyHolder);
        category.setId(generatedKeyHolder.getKey().intValue());
        logger.info("A fost adaugata cateogria {}", category);
        return category;
    }

    public Category editCategory(Category newCategory) {
        jdbcTemplate.update(CategoryQueries.UPDATE_CATEGORY, newCategory.getName(), newCategory.getDescription(), newCategory.getId());
        logger.info("A fost editata categoria {}", newCategory);
        return newCategory;
    }

    public Optional<Category> deleteCategory(int id) {
        Optional<Category> category = getCategoryById(id);
        jdbcTemplate.update(CategoryQueries.DELETE_CATEGORY, id);
        logger.info("A fost stearsa categoria {}", category);
        return category;
    }

    // utils
    private Optional<Category> getCategoryFromResults(List<Category> categories) {
        if (categories != null && !categories.isEmpty()) {
            return Optional.of(categories.get(0));
        }
        return Optional.empty();
    }


    public static class CategoriesRowMapper implements RowMapper<Category> {

        @Override
        public Category mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Category(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("description")
            );
         }
    }
}
