package org.hld.hugold.entity;

import java.util.UUID;

public class CustomerEntity {
    private String id = UUID.randomUUID().toString();
    private String name;
    private String zipcode;


    public CustomerEntity(String name, String zipcode) {
        this.name = name;
        this.zipcode = zipcode;
    }

    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public void setName(String name) {
        this.name = name;
    }
}
