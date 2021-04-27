package org.audreydubois.ayd.entity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Item {
    private @Id @GeneratedValue() Long id;
    private String name;
    public Item(){}
    public Item(String name){
        this.name = name;
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

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(!(obj instanceof Item))
            return false;
        Item item = (Item) obj;
        return Objects.equals(this.id, item.id) && Objects.equals(this.name, item.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name);
    }

    @Override
    public String toString() {
        return  "Item{id="+this.id+", name="+this.name+"}";
    }
}
