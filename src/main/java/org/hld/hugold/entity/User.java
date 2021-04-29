package org.hld.hugold.entity;

import org.hld.hugold.service.GeoApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class User {
	
	private int id;
	private String name;
	private int departementCode;
	private String departement;
	
	
	public int getDepartementCode() {
		return departementCode;
	}


	public void setDepartementCode(int departementCode) {
		this.departementCode = departementCode;
	}


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
	}
	
	public String getDepartement() {
		return departement;
	}
	
	public void setDepartement(String departement) {
		this.departement = departement;
	}

}
