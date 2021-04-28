package com.tp.tommyp.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

/**
 * @author tommy
 * @created 26/04/2021 - 16:50
 * @project tommyp
 */
//@Entity
//@Getter
//@Setter
//public class Item {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//
//    private String name;
//
//    public Item(String name)
//    {
//        this.name = name;
//
//    }
//
//    public Long getId() {
//        return id;
//    }
//}



@Data
@NoArgsConstructor
public class Item {
    private String id = UUID.randomUUID().toString();
    private String name;

    public Item(String name) {
        this.name = name;
    }

    public Object getId() {
        return id;
    }

    public Object getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}