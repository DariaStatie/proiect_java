package com.example.demo.model;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Product {
    @NotNull(message = "Id-ul nu poate fi null!")
    @Range(min = 1, message = "Id-ul trebuie sa fie mai mare decat 0!")
    private int _id;

    @NotNull(message = "Numele nu poate fi  null!")
    @NotBlank(message = "Numele nu poate fi gol!")
    private String _name;

    @NotNull(message = "Descrierea nu poate fi  nulla!")
    @NotBlank(message = "Descrierea nu poate fi goala!")
    private String _description;

    @Range(min = 1, message = "Id-ul categoriei trebuie sa fie mai mare decat 0!")
    private int  _categoryId;

    @NotNull(message = "Pretul nu poate fi null")
    @Range(min = 0, message = "Pretul trebuie sa fie mai mare sau egal cu 0!")
    private float _unitPrice;

    @NotNull(message = "Stockul nu poate fi null!")
    @Range(min = 0, message = "Trebuie sa existe 0 sau mai multe unitati!")
    private int _unitsInStock;

    public Product() {};

    public Product(int _id, String _name, String _description, int _categoryId, float _unitPrice, int _unitsInStock) {
        this._id = _id;
        this._name = _name;
        this._description = _description;
        this._categoryId = _categoryId;
        this._unitPrice = _unitPrice;
        this._unitsInStock = _unitsInStock;
    }

    public int getId() {
        return _id;
    }

    public void setId(int _id) {
        this._id = _id;
    }

    public String getName() {
        return _name;
    }

    public void setName(String _name) {
        this._name = _name;
    }

    public String getDescription() {
        return _description;
    }

    public void setDescription(String _description) {
        this._description = _description;
    }

    public int getCategoryId() {
        return _categoryId;
    }

    public void setCategoryId(int _categoryId) {
        this._categoryId = _categoryId;
    }

    public float getUnitPrice() {
        return _unitPrice;
    }

    public void setUnitPrice(float _unitPrice) {
        this._unitPrice = _unitPrice;
    }

    public int getUnitsInStock() {
        return _unitsInStock;
    }

    public void setUnitsInStock(int _unitsInStock) {
        this._unitsInStock = _unitsInStock;
    }

    @Override
    public String toString() {
        return "Product{" +
                "_id=" + _id +
                ", _name='" + _name + '\'' +
                ", _description='" + _description + '\'' +
                ", _categoryId=" + _categoryId +
                ", _unitPrice=" + _unitPrice +
                ", _unitsInStock=" + _unitsInStock +
                '}';
    }
}
