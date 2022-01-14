package com.example.demo.service;

import com.example.demo.exceptions.ObjectNotFoundException;
import com.example.demo.model.Customer;
import com.example.demo.queries.CustomerQueries;
import com.example.demo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    public List<Customer> getCustomers() {
        return customerRepository.getCustomers();
    }

    public Optional<Customer> getCustomerById(int id) {
        Optional<Customer> existingCustomer = customerRepository.getCustomerById(id);

        if (existingCustomer.isEmpty()) {
            throw new ObjectNotFoundException("Clientul cu id-ul specificat nu exista");
        }

        return existingCustomer;
    }

    public Optional<Customer> getCustomerByName(String firstName, String lastName) {
        Optional<Customer> existingCustomer = customerRepository.getCustomerByName(firstName, lastName);

        if (existingCustomer.isEmpty()) {
            throw new ObjectNotFoundException("Clientul cu numele specificat nu exista");
        }

        return existingCustomer;
    }

    public Customer addCustomer(Customer customer) {
        return customerRepository.addCustomer(customer);
    }

    public Customer editCustomer(Customer newCustomer) {
        Optional<Customer> existingCustomer = customerRepository.getCustomerById(newCustomer.getId());

        if (existingCustomer.isEmpty()) {
            throw new ObjectNotFoundException("Clientul cu id-ul specificat nu exista");
        }

        return customerRepository.editCustomer(newCustomer);
    }

    public Optional<Customer> deleteCustomer(int id) {
        Optional<Customer> existingCustomer = customerRepository.getCustomerById(id);

        if (existingCustomer.isEmpty()) {
            throw new ObjectNotFoundException("Clientul cu id-ul specificat nu exista");
        }

        return customerRepository.deleteCustomer(id);
    }
}
