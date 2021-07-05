package com.example.Abo.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class User {
	@Id
	private String id;
	
	private String name ;
	private String departement;
	private String departementCode;
	
	
	public User(String name, String departement, String departementCode) {
		super();
		this.setId();
		this.setName(name);
		this.setDepartement(departement);
		this.setDepartementCode(departementCode);
	}
	
	public String getId() {
		return id;
	}
	
	public void setId() {
		this.id = UUID.randomUUID().toString() ;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartement() {
		return departement;
	}

	public void setDepartement(String departement) {
		this.departement = departement;
	}

	public String getDepartementCode() {
		return departementCode;
	}

	public void setDepartementCode(String departementCode) {
		this.departementCode = departementCode;
	}

	
	

}
