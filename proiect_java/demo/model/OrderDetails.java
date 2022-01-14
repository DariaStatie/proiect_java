package com.example.demo.model;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class OrderDetails {
    @NotNull(message = "Id-ul comenzii nu poate fi null!")
    private int _orderId;

    @NotNull(message = "Id-ul produsului nu poate fi null!")
    private int _productId;

    @NotNull(message = "Cantitatea produsului nu poate fi null!")
    @Range(min = 1, message = "Trebuie sa fie cel putin 1 produs!")
    private int _quantity;

    public OrderDetails() {}

    public OrderDetails(int _orderId, int _productId, int _quantity) {
        this._orderId = _orderId;
        this._productId = _productId;
        this._quantity = _quantity;
    }

    public int getOrderId() {
        return _orderId;
    }

    public void setOrderId(int _orderId) {
        this._orderId = _orderId;
    }

    public int getProductId() {
        return _productId;
    }

    public void setProductId(int _productId) {
        this._productId = _productId;
    }

    public int getQuantity() {
        return _quantity;
    }

    public void setQuantity(int _quantity) {
        this._quantity = _quantity;
    }

    @Override
    public String toString() {
        return "OrderDetails{" +
                "_orderId=" + _orderId +
                ", _productId=" + _productId +
                ", _quantity=" + _quantity +
                '}';
    }
}
