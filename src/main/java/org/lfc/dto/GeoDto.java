package org.lfc.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GeoDto implements Serializable {
	
	private long id;

	private String nom;
}
