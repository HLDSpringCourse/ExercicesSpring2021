package org.pyl.pylspring.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Item {

    @Id
    private Long id;

    @Column
    private String name;

    @Column
    private String regionCode;

    @Column
    private String regionName;

    public Item() {
        super();
    }

    public Item(Long id, String name, String regionCode, String regionName) {
        this.id = id;
        this.name = name;
        this.regionCode = regionCode;
        this.regionName = regionName;
    }

}
