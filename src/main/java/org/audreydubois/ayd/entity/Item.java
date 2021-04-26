package org.audreydubois.ayd.entity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Item {
    private @Id @GeneratedValue() Long id;
    private String name;
    public Item(){}
    public Item(Long id, String name){
        this.id = id;
        this.name = name;
    }


}
