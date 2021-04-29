package org.ld.leonied.entity;

import javax.persistence.*;

@Entity
@Table(name = "MyOrder")
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "Id", nullable = false)
    private Long id;

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "City", nullable = true)
    private String city;

    @Column(name = "Lattitude", nullable = true)
    private Integer lattitude = null;

    @Column(name = "Longitude", nullable = true)
    private Integer longitude = null;

    public Order() {}

    public Order(String name) {
        this.name = name;
    }

    public Order(String name, String city) {
        this.name = name;
        this.city = city;
    }

    public Order(String name, int lattitude, int longitude) {
        this.name = name;
        this.lattitude = lattitude;
        this.longitude = longitude;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getLattitude() {
        return lattitude;
    }

    public void setLattitude(Integer lattitude) {
        this.lattitude = lattitude;
    }

    public Integer getLongitude() {
        return longitude;
    }

    public void setLongitude(Integer longitude) {
        this.longitude = longitude;
    }
}
