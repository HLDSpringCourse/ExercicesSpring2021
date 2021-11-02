package org.hld.hugold.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity(name = "Customer")
public class CustomerEntity {
    @Id
    private String id = UUID.randomUUID().toString();
    @Column(name = "name")
    private String name;
    @Column(name = "zipcode")
    private String zipcode;
    @Column(name ="city")
    private String city;

    public CustomerEntity(String name, String zipcode ) {
        this.name = name;
        this.zipcode = zipcode;
        this.city = city;
    }

    // GETTERS
    public String getId() {
        return id;
    }


    public String getName() {
        return name;
    }
    public String getZipcode() {
        return zipcode;
    }

    public String getCity() {
        return city;
    }

    //SETTERS
    public void setName(String name) {
        this.name = name;
    }
    public void setZipcode(String zipCode) {
        this.zipcode = zipCode;
    }
    public void setCity(String city) {
        this.city = city;
    }

}
