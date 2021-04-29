package org.pyl.pylspring.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO implements Serializable {

    private Long id;

    private String name;

    private String regionCode;

    private String regionName;

    public ItemDTO(String name, String regionCode) {
        this.name = name;
        this.regionCode = regionCode;
    }
}
 
