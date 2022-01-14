package com.example.demo.model;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

public class ProductQuantity {
    @NotNull(message = "Id-ul nu poate fi null!")
    @Range(min = 1, message = "Id-ul trebuie sa fie mai mare decat 0!")
    private int _productId;

    @NotNull(message = "Cantitatea nu poate fi nulla!")
    @Range(min = 1, message = "Cantitatea trebuie sa fie mai mare decat 0!")
    private int _quantity;

    public ProductQuantity() {}

    public ProductQuantity(int _productId, int _quantity) {
        this._productId = _productId;
        this._quantity = _quantity;
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
        return "ProductQuantity{" +
                "_productId=" + _productId +
                ", _quantity=" + _quantity +
                '}';
    }
}
