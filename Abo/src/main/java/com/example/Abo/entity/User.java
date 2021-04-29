package com.example.Abo.entity;


public class User {
	
	private String id ;
	private String name ;
	private String departement;
	private String departementCode;
	
	public User(String id, String name, String departement) {
		super();
		this.id = id;
		this.setName(name);
		this.setDepartement(departement);
	}

	public String getId() {
		// TODO Auto-generated method stub
		return id;
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
