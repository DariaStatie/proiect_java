package com.example.demo.model;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Customer {
    @NotNull(message = "Id-ul nu poate fi null!")
    @Range(min = 1, message = "Id-ul trebuie sa fie mai mare decat 0!")
    private int _id;

    @NotNull(message = "Prenumele nu poate fi  null!")
    @NotBlank(message = "Prenumele nu poate fi gol!")
    private String _firstName;

    @NotNull(message = "Numele nu poate fi  null!")
    @NotBlank(message = "Numele nu poate fi gol!")
    private String _lastName;

    @NotNull(message = "Adresa nu poate fi  null!")
    @NotBlank(message = "Adresa nu poate fi goala!")
    private String _address;

    @NotNull(message = "Numarul de telefon nu poate fi  null!")
    @NotBlank(message = "Numarul de telefon nu poate fi gol!")
    private String _phoneNumber;

    @NotNull(message = "Emailul nu poate fi  null!")
    @NotBlank(message = "Emailul nu poate fi gol!")
    private String _email;

    public Customer() {}

    public Customer(int _id, String _firstName, String _lastName, String _address, String _phoneNumber, String _email) {
        this._id = _id;
        this._firstName = _firstName;
        this._lastName = _lastName;
        this._address = _address;
        this._phoneNumber = _phoneNumber;
        this._email = _email;
    }

    public int getId() {
        return _id;
    }

    public void setId(int _id) {
        this._id = _id;
    }

    public String getFirstName() {
        return _firstName;
    }

    public void setFirstName(String _firstName) {
        this._firstName = _firstName;
    }

    public String getLastName() {
        return _lastName;
    }

    public void setLastName(String _lastName) {
        this._lastName = _lastName;
    }

    public String getAddress() {
        return _address;
    }

    public void setAddress(String _address) {
        this._address = _address;
    }

    public String getPhoneNumber() {
        return _phoneNumber;
    }

    public void setPhoneNumber(String _phoneNumber) {
        this._phoneNumber = _phoneNumber;
    }

    public String getEmail() {
        return _email;
    }

    public void setEmail(String _email) {
        this._email = _email;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "_id=" + _id +
                ", _firstName='" + _firstName + '\'' +
                ", _lastName='" + _lastName + '\'' +
                ", _address='" + _address + '\'' +
                ", _phoneNumber='" + _phoneNumber + '\'' +
                ", _email='" + _email + '\'' +
                '}';
    }
}
