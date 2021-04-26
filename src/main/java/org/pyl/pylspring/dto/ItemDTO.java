package org.pyl.pylspring.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ItemDTO implements Serializable {

    private Long id;

    private String Name;

    public ItemDTO() {
    }

    public ItemDTO(Long id, String name) {
        this.id = id;
        Name = name;
    }
}
