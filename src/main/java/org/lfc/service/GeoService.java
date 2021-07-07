package org.lfc.service;


import org.lfc.dto.GeoDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GeoService {
	public GeoDto foundCity(int zipcode)
	{
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<GeoDto> geoDto = restTemplate
			.getForEntity("https://geo.api.gouv.fr/departements/" + zipcode + "?fields=nom,codesPostaux&format=json&geometry=centre", GeoDto.class);
		// assertThat(geoDto.getName(), notNullValue());
		// assertThat(geoDto.getId(), is(1L));
		return geoDto.getBody();
	}
}
