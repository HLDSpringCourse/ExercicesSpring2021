package org.audreydubois.ayd.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Region {
    private @Id
    @GeneratedValue Long id;

    private @Column String region;

    private @Column String regionCode;

    @OneToMany(mappedBy = "region")
    private List<Item> items = new ArrayList<>();

    public Region(String region, String code){
        this.region = region;
        this.regionCode = code;
    }
}
