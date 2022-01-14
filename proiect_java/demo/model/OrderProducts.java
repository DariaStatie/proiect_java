package com.example.demo.model;

import javax.validation.constraints.NotEmpty;
import java.util.List;

public class OrderProducts extends Order {
    @NotEmpty(message = "Product list must have at least one entry!")
    private List<ProductQuantity> _products;

    public OrderProducts() { super(); }

    public OrderProducts(List<ProductQuantity> _products) {
        this._products = _products;
    }

    public OrderProducts(Order order, List<ProductQuantity> _products) {
        super(order.getId(), order.getCustomerId(), order.getShipperId(), order.getOrderDate());
        this._products = _products;
    }

    public OrderProducts(int _id, int _customerId, int _shipperId, String _orderDate, List<ProductQuantity> _products) {
        super(_id, _customerId, _shipperId, _orderDate);
        this._products = _products;
    }

    public List<ProductQuantity> getProducts() {
        return _products;
    }

    public void setProducts(List<ProductQuantity> _products) {
        this._products = _products;
    }

    @Override
    public String toString() {
        return "OrderProducts{" +
                "_products=" + _products +
                '}';
    }
}
