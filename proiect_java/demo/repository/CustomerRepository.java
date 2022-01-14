package com.example.demo.repository;

import com.example.demo.model.Customer;
import com.example.demo.queries.CustomerQueries;
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
public class CustomerRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final Logger logger = LoggerFactory.getLogger(CustomerRepository.class);

    public List<Customer> getCustomers() {
        List<Customer> customers = jdbcTemplate.query(CustomerQueries.GET_CUSTOMERS, new CustomerRepository.CustomersRowMapper());
        logger.info("Au fost returnati clientii {}", customers);
        return customers;
    }

    public Optional<Customer> getCustomerById(int id) {
        List<Customer> customers = jdbcTemplate.query(CustomerQueries.GET_CUSTOMER_BY_ID, new CustomerRepository.CustomersRowMapper(), id);
        Optional<Customer> customer = getCustomerFromResults(customers);
        logger.info("A fost returnat clientul {}", customer);
        return customer;
    }

    public Optional<Customer> getCustomerByName(String firstName, String lastName) {
        List<Customer> customers = jdbcTemplate.query(CustomerQueries.GET_CUSTOMER_BY_NAME, new CustomerRepository.CustomersRowMapper(), firstName, lastName);
        Optional<Customer> customer = getCustomerFromResults(customers);
        logger.info("A fost returnat clientul {}", customer);
        return customer;
    }

    public Customer addCustomer(Customer customer) {
        PreparedStatementCreator preparedStatementCreator = (connection) -> {
            PreparedStatement preparedStatement = connection.prepareStatement(CustomerQueries.ADD_CUSTOMER, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1, null);
            preparedStatement.setString(2, customer.getFirstName());
            preparedStatement.setString(3, customer.getLastName());
            preparedStatement.setString(4, customer.getAddress());
            preparedStatement.setString(5, customer.getPhoneNumber());
            preparedStatement.setString(6, customer.getEmail());
            return preparedStatement;
        };

        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(preparedStatementCreator, generatedKeyHolder);
        customer.setId(generatedKeyHolder.getKey().intValue());
        logger.info("A fost adaugat clientul {}", customer);
        return customer;
    }

    public Customer editCustomer(Customer newCustomer) {
        jdbcTemplate.update(CustomerQueries.UPDATE_CUSTOMER, newCustomer.getFirstName(), newCustomer.getLastName(), newCustomer.getAddress(),
                newCustomer.getPhoneNumber(), newCustomer.getEmail(), newCustomer.getId());
        logger.info("A fost editat clientul {}", newCustomer);
        return newCustomer;
    }

    public Optional<Customer> deleteCustomer(int id) {
        Optional<Customer> customer = getCustomerById(id);
        jdbcTemplate.update(CustomerQueries.DELETE_CUSTOMER, id);
        logger.info("A fost sters clientul {}", customer);
        return customer;
    }

    // utils
    private Optional<Customer> getCustomerFromResults(List<Customer> customers) {
        if (customers != null && !customers.isEmpty()) {
            return Optional.of(customers.get(0));
        }
        return Optional.empty();
    }

    public static class CustomersRowMapper implements RowMapper<Customer> {

        @Override
        public Customer mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Customer(
                    resultSet.getInt("id"),
                    resultSet.getString("firstName"),
                    resultSet.getString("lastName"),
                    resultSet.getString("address"),
                    resultSet.getString("phoneNumber"),
                    resultSet.getString("email")
            );
        }
    }
}
