package org.lfc.service;


import org.lfc.dto.GeoDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GeoService {
	public GeoDto foundCity(int zipcode)
	{
		RestTemplate restTemplate = new RestTemplate();
		
		GeoDto geoDto = restTemplate
			.getForObject("https://geo.api.gouv.fr/communes?codePostal=" + zipcode + "&fields=nom,codesPostaux&format=json&geometry=centre", GeoDto.class);
		// assertThat(geoDto.getName(), notNullValue());
		// assertThat(geoDto.getId(), is(1L));
		return geoDto;
	}
}
