package org.audreydubois.ayd.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemDTO {
    private Long id;

    private  String name;

    private  String regionCode;

}
