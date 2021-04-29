package org.hld.hugold.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "us")
@NoArgsConstructor
public class User {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column
	private String name;
	
	@Column
	private int departementCode;
	
	@Column
	private String departement;
	



	public User( String name, int departementCode) {
		super();
		this.name = name;
		this.departementCode = departementCode;
	}


}
