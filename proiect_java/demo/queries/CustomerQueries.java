package com.example.demo.queries;

public class CustomerQueries {
    public final static String GET_CUSTOMERS = "SELECT * FROM customers";
    public final static String GET_CUSTOMER_BY_ID = "SELECT * FROM customers WHERE id = ?";
    public final static String GET_CUSTOMER_BY_NAME = "SELECT * FROM customers WHERE firstName LIKE ? and lastName LIKE ?";

    public final static String ADD_CUSTOMER = "INSERT INTO customers(id, firstName, lastName, address, phoneNumber, email) VALUES (?, ?, ?, ?, ?, ?)";

    public final static String UPDATE_CUSTOMER = "UPDATE customers SET firstName = ?, lastName = ?, address = ?, phoneNumber = ?, email = ? WHERE id = ?";

    public final static String DELETE_CUSTOMER = "DELETE from customers WHERE id = ?";
}
