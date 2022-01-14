package com.example.demo.repository;

import com.example.demo.model.Order;
import com.example.demo.model.OrderDetails;
import com.example.demo.model.OrderProducts;
import com.example.demo.model.ProductQuantity;
import com.example.demo.queries.OrderQueries;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class OrderRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final Logger logger = LoggerFactory.getLogger(ProductRepository.class);

    public List<OrderProducts> getOrders() {
        List<Order> orders = jdbcTemplate.query(OrderQueries.GET_ORDERS, new OrderRowMapper());
        List<OrderProducts> orderProducts = new ArrayList<OrderProducts>();

        for(Order order : orders) {
            orderProducts.add(getOrderProductsFromOrder(order));
        }
        logger.info("Au fost returnate comenzile {}", orderProducts);
        return orderProducts;
    }

    public Optional<OrderProducts> getOrderById(int id) {
        OrderProducts orderProducts = getOrder(id);
        logger.info("A fost returnata comanda {}", orderProducts);
        if (orderProducts != null) {
            return Optional.of(orderProducts);
        }
        return Optional.empty();
    }

    public Optional<List<OrderProducts>> getCustomerOrders(int customerId) {
        List<Order> orders = jdbcTemplate.query(OrderQueries.GET_CUSTOMER_ORDERS, new OrderRowMapper(), customerId);
        if (orders.isEmpty()) {
            logger.info("Nu au fost gasite comenzi ale clientului {}", customerId);
            return Optional.empty();
        }
        List<OrderProducts> orderProducts = new ArrayList<OrderProducts>();
        for (Order order : orders) {
            orderProducts.add(getOrderProductsFromOrder(order));
        }
        logger.info("Au fost returnate comenzile {} clientului {}", orderProducts, customerId);
        return Optional.of(orderProducts);
    }

    public Optional<List<OrderProducts>> getOrdersWithProduct(int productId) {
        List<OrderDetails> orderDetails = jdbcTemplate.query(OrderQueries.GET_ALL_ORDER_DETAILS_BY_PRODUCT_ID, new OrderDetailsRowMapper(), productId);
        List<OrderProducts> orderProducts = new ArrayList<OrderProducts>();
        for (OrderDetails orderDet : orderDetails) {
            OrderProducts orderProd = getOrder(orderDet.getOrderId());
            if (orderProd != null) {
                orderProducts.add(orderProd);
            }
        }
        if (!orderProducts.isEmpty()) {
            return Optional.of(orderProducts);
        }
        return Optional.empty();
    }

    public Optional<List<OrderProducts>> getShipperOrders(int shipperId) {
        List<Order> orders = jdbcTemplate.query(OrderQueries.GET_SHIPPER_ORDERS, new OrderRowMapper(), shipperId);
        if (orders.isEmpty()) {
            logger.info("Nu au fost gasite comenzi ale transportatorului {}", shipperId);
            return Optional.empty();
        }
        List<OrderProducts> orderProducts = new ArrayList<OrderProducts>();
        for (Order order : orders) {
            orderProducts.add(getOrderProductsFromOrder(order));
        }
        logger.info("Au fost returnate comenzile {} transportatorului {}", orderProducts, shipperId);
        return Optional.of(orderProducts);
    }

    public OrderProducts addOrder(OrderProducts orderProducts) {
        Order order = new Order(orderProducts.getId(), orderProducts.getCustomerId(), orderProducts.getShipperId(), orderProducts.getOrderDate());
        order.setId(addOrder(order).getId());

        addOrderDetails(order.getId(), orderProducts.getProducts());
        logger.info("A fost adaugata comanda {}", orderProducts);
        return orderProducts;
    }

    public OrderProducts editOrder(OrderProducts newOrderProducts) {
        jdbcTemplate.update(OrderQueries.UPDATE_ORDER, newOrderProducts.getCustomerId(), newOrderProducts.getShipperId(), newOrderProducts.getOrderDate(), newOrderProducts.getId());

        for(ProductQuantity productQuantity : newOrderProducts.getProducts()) {
            jdbcTemplate.update(OrderQueries.ADD_OR_UPDATE_ORDER_DETAILS, newOrderProducts.getId(), productQuantity.getProductId(), productQuantity.getQuantity(), newOrderProducts.getId(), productQuantity.getProductId(), productQuantity.getQuantity());
        }
        logger.info("A fost editata comanda {}", newOrderProducts);
        return newOrderProducts;
    }

    public Optional<OrderProducts> deleteOrder(int id) {
        Optional<OrderProducts> order = getOrderById(id);
        jdbcTemplate.update(OrderQueries.DELETE_ORDER_DETAILS, id);
        jdbcTemplate.update(OrderQueries.DELETE_ORDER, id);
        logger.info("A fost stearsa comanda {}", order);
        return order;
    }

    // utils
    private Order getOrderFromResults(List<Order> orders) {
        if (orders != null && !orders.isEmpty()) {
            return orders.get(0);
        }
        return null;
    }

    private Order addOrder(Order order) {
        PreparedStatementCreator preparedStatementCreator = (connection) -> {
            PreparedStatement preparedStatement = connection.prepareStatement(OrderQueries.ADD_ORDER, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1, null);
            preparedStatement.setInt(2, order.getCustomerId());
            preparedStatement.setInt(3, order.getShipperId());
            preparedStatement.setString(4, order.getOrderDate());
            return preparedStatement;
        };

        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(preparedStatementCreator, generatedKeyHolder);
        order.setId(generatedKeyHolder.getKey().intValue());
        logger.info("A fost adaugata comanda {}", order);
        return order;
    }

    private OrderProducts getOrder(int id) {
        List<Order> orders = jdbcTemplate.query(OrderQueries.GET_ORDER_BY_ID, new OrderRowMapper(), id);
        Order order = getOrderFromResults(orders);
        if (order != null) {
            return getOrderProductsFromOrder(order);
        }
        return null;
    }

    private void addOrderDetails(int orderId, List<ProductQuantity> productIds) {
        for (ProductQuantity product : productIds) {
            jdbcTemplate.update(OrderQueries.ADD_ORDER_DETAILS, orderId, product.getProductId(), product.getQuantity());
            logger.info("A fost adaugat combo-ul idComanda - idProdus: {} - {}, cantitateProdus: {}", orderId, product.getProductId(), product.getQuantity());
        }
    }

    private OrderProducts getOrderProductsFromOrder(Order order) {
        List<OrderDetails> orderDetails = jdbcTemplate.query(OrderQueries.GET_ALL_ORDER_DETAILS_BY_ORDER_ID, new OrderDetailsRowMapper(), order.getId());
        List<ProductQuantity> prodQuantity = new ArrayList<ProductQuantity>();
        for(OrderDetails orderDetail: orderDetails) {
            prodQuantity.add(new ProductQuantity(orderDetail.getProductId(), orderDetail.getQuantity()));
        }
        return new OrderProducts(order, prodQuantity);
    }

    public static class OrderRowMapper implements RowMapper<Order> {

        @Override
        public Order mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Order(
                    resultSet.getInt("id"),
                    resultSet.getInt("customerId"),
                    resultSet.getInt("shipperId"),
                    resultSet.getString("orderDate")
            );
        }
    }

    public static class OrderDetailsRowMapper implements RowMapper<OrderDetails> {

        @Override
        public OrderDetails mapRow(ResultSet resultSet, int i) throws SQLException {
            return new OrderDetails(
                    resultSet.getInt("orderId"),
                    resultSet.getInt("productId"),
                    resultSet.getInt("quantity")
            );
        }
    }

}
