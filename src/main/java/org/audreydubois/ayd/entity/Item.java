package org.audreydubois.ayd.entity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Item {
    private @Id @GeneratedValue() Long id;

    private @Column String name;

    private @Column String region;

    private @Column String regionCode;

    public Item(){}

    public Item(String name, String regionCode, String region){
        this.name = name;
        this.regionCode = regionCode;
        this.region = region;
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

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(!(obj instanceof Item))
            return false;
        Item item = (Item) obj;
        return Objects.equals(this.id, item.id) && Objects.equals(this.name, item.name)
                && Objects.equals(this.regionCode, item.regionCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.regionCode);
    }

    @Override
    public String toString() {
        return  "Item{id="+this.id+", name="+this.name+", regionCode="+this.regionCode+"}";
    }
}
