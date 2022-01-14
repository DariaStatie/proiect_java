package com.example.demo.model;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Category {
    @NotNull(message = "Id-ul nu poate fi null!")
    @Range(min = 1, message = "Id-ul trebuie sa fie mai mare decat 0!")
    private int id;

    @NotNull(message = "Numele nu poate fi  null!")
    @NotBlank(message = "Numele nu poate fi gol!")
    private String name;

    @NotNull(message = "Descrierea nu poate fi  nulla!")
    @NotBlank(message = "Descrierea nu poate fi goala!")
    private String description;

    public Category() {}

    public Category(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
