package org.pyl.pylspring.dao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemDAO {

    private Long id;

    private String Name;

    public ItemDAO() {
    }

    public ItemDAO(Long id, String name) {
        this.id = id;
        Name = name;
    }
}
