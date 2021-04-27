package org.pyl.pylspring.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Item {

    @Id
    private Long id;

    private String Name;

    private String regionCode;

    public Item() {
        super();
    }

    public Item(Long id, String name) {
        this.id = id;
        Name = name;
    }

}
