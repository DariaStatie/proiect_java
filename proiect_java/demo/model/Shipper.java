package com.example.demo.model;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Shipper {
    @NotNull(message = "Id-ul nu poate fi null!")
    @Range(min = 1, message = "Id-ul trebuie sa fie mai mare decat 0!")
    private int _id;

    @NotNull(message = "Numele nu poate fi  null!")
    @NotBlank(message = "Numele nu poate fi gol!")
    private String _companyName;

    public Shipper(int _id, String _companyName) {
        this._id = _id;
        this._companyName = _companyName;
    }

    public int getId() {
        return _id;
    }

    public void setId(int _id) {
        this._id = _id;
    }

    public String getCompanyName() {
        return _companyName;
    }

    public void setCompanyName(String _companyName) {
        this._companyName = _companyName;
    }

    @Override
    public String toString() {
        return "Shipper{" +
                "_id=" + _id +
                ", _companyName='" + _companyName + '\'' +
                '}';
    }
}
