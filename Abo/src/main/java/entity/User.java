package entity;

import javax.persistence.Entity;

import controller.userController;
import service.userService;


@Entity
public class User {
	
	private String id ;
	private String name ;
	
	public User(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public String getId() {
		// TODO Auto-generated method stub
		return id;
	}


}
