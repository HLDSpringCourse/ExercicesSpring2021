package com.alik.alik.entity;


import lombok.Data;

@Data
public class Customer {
    private int id;
    private String name;
    private String city;
    private String zipCode;

    public Customer() {

    }

    public Customer(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
