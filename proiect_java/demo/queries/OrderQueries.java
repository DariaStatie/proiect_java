package com.example.demo.queries;

public class OrderQueries {
    public final static String GET_ORDERS = "SELECT * FROM orders";
    public final static String GET_ORDER_BY_ID = "SELECT * FROM orders WHERE id = ?";

    public final static String GET_ALL_ORDER_DETAILS_BY_ORDER_ID = "SELECT * FROM orderdetails WHERE orderId = ?";
    public final static String GET_ALL_ORDER_DETAILS_BY_PRODUCT_ID = "SELECT * FROM orderdetails WHERE productId = ?";

    public final static String GET_CUSTOMER_ORDERS = "SELECT * FROM orders WHERE customerId = ?";
    public final static String GET_SHIPPER_ORDERS = "SELECT * FROM orders WHERE shipperID = ?";

    public final static String ADD_ORDER = "INSERT INTO orders(id, customerId, shipperId, orderDate) VALUES (?, ?, ?, ?)";

    public final static String ADD_ORDER_DETAILS = "INSERT INTO orderdetails(orderId, productId, quantity) VALUES (?, ?, ?)";

    public final static String UPDATE_ORDER = "UPDATE orders SET customerID = ?, shipperID = ?, orderDate = ? WHERE id = ?";

    public final static String ADD_OR_UPDATE_ORDER_DETAILS = "INSERT INTO orderdetails(orderID, productId, quantity) VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE orderId = ?, productId = ?, quantity = ?";

    public final static String DELETE_ORDER = "DELETE from orders where id = ?";

    public final static String DELETE_ORDER_DETAILS = "DELETE from orderdetails where orderId = ?";
}
