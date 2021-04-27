package org.pyl.pylspring.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ItemDTO implements Serializable {

    private Long id;

    private String name;

    private String regionCode;

    private String regionName;

    public ItemDTO() {
        super();
    }

    public ItemDTO(Long id, String name, String regionCode, String regionName) {
        this.id = id;
        this.name = name;
        this.regionCode = regionCode;
        this.regionName = regionName;
    }
}
