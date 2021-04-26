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

}
