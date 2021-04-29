package org.aubm.aubertma.entity;

public class Customer {
    private int id;
    private String name;
    private String codeINSEE;
    private String city;

    public Customer(int id, String name, String code) {
        this.id = id;
        this.name = name;
        codeINSEE = code;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public String getCodeINSEE() {
        return codeINSEE;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
