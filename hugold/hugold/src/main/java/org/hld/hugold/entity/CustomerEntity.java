package org.hld.hugold.entity;

import java.util.UUID;

public class CustomerEntity {
    private String id = UUID.randomUUID().toString();
    private String name;
    private String zipCode;
    private String city;

    public CustomerEntity(String name, String zipCode) {
        this.name = name;
        this.zipCode = zipCode;
    }

    // GETTERS
    public String getId() {
        return id;
    }


    public String getName() {
        return name;
    }
    public String getZipCode() {
        return zipCode;
    }

    public String getCity() {
        return city;
    }

    //SETTERS
    public void setName(String name) {
        this.name = name;
    }
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
    public void setCity(String city) {
        this.city = city;
    }

}
