package com.example.demo.service;

import com.example.demo.exceptions.ObjectNotFoundException;
import com.example.demo.model.OrderProducts;
import com.example.demo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    public List<OrderProducts> getOrders() {
        return orderRepository.getOrders();
    }

    public Optional<OrderProducts> getOrderById(int id) {
        Optional<OrderProducts> existingOrderProducts = orderRepository.getOrderById(id);

        if (existingOrderProducts.isEmpty()) {
            throw new ObjectNotFoundException("Nu exista produsul cu id-ul specificat!");
        }

        return existingOrderProducts;
    }

    public Optional<List<OrderProducts>> getCustomerOrders(int customerId) {
        Optional<List<OrderProducts>> orderProducts = orderRepository.getCustomerOrders(customerId);

        if (orderProducts.isEmpty()) {
            throw new ObjectNotFoundException("Nu exista comenzi ale clientului cu id-ul specificat!");
        }

        return orderProducts;
    }

    public Optional<List<OrderProducts>> getOrdersWithProduct(int productId) {
        Optional<List<OrderProducts>> orderProducts = orderRepository.getOrdersWithProduct(productId);

        if (orderProducts.isEmpty()) {
            throw new ObjectNotFoundException("Nu exista comnenzi ce contin produsul sepcificat!");
        }

        return orderProducts;
    }

    public Optional<List<OrderProducts>> getShipperOrders(int shipperId) {
        Optional<List<OrderProducts>> orderProducts = orderRepository.getShipperOrders(shipperId);

        if (orderProducts.isEmpty()) {
            throw new ObjectNotFoundException("Nu exista comenzi ale clientului cu id-ul specificat!");
        }

        return orderProducts;
    }

    public OrderProducts addOrder(OrderProducts orderProducts) { return orderRepository.addOrder(orderProducts); }

    public OrderProducts editOrder(OrderProducts newOrderProducts) {
        Optional<OrderProducts> existingOrderProducts = orderRepository.getOrderById(newOrderProducts.getId());

        if (existingOrderProducts.isEmpty()) {
            throw new ObjectNotFoundException("Nu exista comanda cu id-ul sepcificat!");
        }

        return orderRepository.editOrder(newOrderProducts);
    }

    public Optional<OrderProducts> deleteOrder(int id) {
        Optional<OrderProducts> existingOrderProducts = orderRepository.getOrderById(id);

        if (existingOrderProducts.isEmpty()) {
            throw new ObjectNotFoundException("Nu exista comanda cu id-ul sepcificat!");
        }

        return orderRepository.deleteOrder(id);
    }
}
