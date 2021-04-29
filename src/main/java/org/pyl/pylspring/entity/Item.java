package org.pyl.pylspring.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Item {

    @GeneratedValue
    @Id
    private Long id;

    @Column
    private String name;

    @Column
    private String regionCode;

    @Column
    private String regionName;

    public Item(String name, String regionCode, String regionName) {
        this.name = name;
        this.regionCode = regionCode;
        this.regionName = regionName;
    }

}
