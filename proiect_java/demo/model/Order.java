package com.example.demo.model;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

public class Order {
    @NotNull(message = "Id-ul nu poate fi null!")
    @Range(min = 1, message = "Id-ul trebuie sa fie mai mare decat 0!")
    private int _id;

    @NotNull(message = "Id-ul clientului nu poate fi null!")
    @Range(min = 1, message = "Id-ul clientului trebuie sa fie mai mare decat 0!")
    private int _customerId;

    @NotNull(message = "Id-ul transportatorului nu poate fi null!")
    @Range(min = 1, message = "Id-ul transportatorului trebuie sa fie mai mare decat 0!")
    private int _shipperId;

    private String _orderDate;

    public Order() {}

    public Order(int _id, int _customerId, int _shipperId, String _orderDate) {
        this._id = _id;
        this._customerId = _customerId;
        this._shipperId = _shipperId;
        this._orderDate = _orderDate;
    }

    public int getId() {
        return _id;
    }

    public void setId(int _id) {
        this._id = _id;
    }

    public int getCustomerId() {
        return _customerId;
    }

    public void setCustomerId(int _customerId) {
        this._customerId = _customerId;
    }

    public String getOrderDate() {
        return _orderDate;
    }

    public void setOrderDate(String _orderDate) {
        this._orderDate = _orderDate;
    }

    public int getShipperId() {
        return _shipperId;
    }

    public void setShipperId(int _shipperId) {
        this._shipperId = _shipperId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "_id=" + _id +
                ", _customerId=" + _customerId +
                ", _shipperId=" + _shipperId +
                ", _orderDate='" + _orderDate + '\'' +
                '}';
    }
}
