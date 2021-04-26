package org.hld.hugold.controller;

import java.util.ArrayList;
import java.util.List;
import org.hld.hugold.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

	@GetMapping(path = "/users")
	public List<User> getUsers(){
		List<User> liste = new ArrayList<User>();
		liste.add(new User(123, "michel"));
		liste.add(new User(234, "jacques"));
		return liste;
	}
	
	@GetMapping(path = "/{id}")
	public User getUser(@PathVariable("id") int id) {
		return new User(456,"Bertrand");
	}
	@PostMapping
	public ResponseEntity<User> addUser(@RequestBody User user){	
		return ResponseEntity.status(HttpStatus.CREATED).body(user);
	}
	@PutMapping
	public User updateUser( @RequestBody User user) {
		
		return user;
	}
	
	@DeleteMapping(path= "/{id}")
		public int deleteUser(@PathVariable("id")int id) {
		return id;
	}
	
}
