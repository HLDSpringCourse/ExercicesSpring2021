package org.hld.hugold.entity;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class User {
	
	private int id;
	private String name;
	private int departementCode;
	private String departement;
	private static final String urlAPIdepartement = "https://geo.api.gouv.fr/departements/";
	
	private static RestTemplate restTemplate= new RestTemplate();
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	public User(int id, String name, int departementCode) {
		super();
		this.id = id;
		this.name = name;
		this.departementCode = departementCode;
		this.departement=setDepartement();
	}
	
	public String getDepartement() {
		return departement;
	}




	public String setDepartement(){
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
