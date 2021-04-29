package org.hld.hugold.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class GeoApi {
	
	private String urlAPIdepartement = "https://geo.api.gouv.fr/departements/";
	private RestTemplate restTemplate= new RestTemplate();
	
	public  String findDepartement(int departementCode){
		ResponseEntity<String> response =restTemplate.getForEntity(urlAPIdepartement + departementCode, String.class);
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = null;
		try {
			root = mapper.readTree(response.getBody());
		} catch (Exception e) {
			e.printStackTrace();
		}
		JsonNode name = root.path("nom"); 
		
		return name.asText();
	}
}
