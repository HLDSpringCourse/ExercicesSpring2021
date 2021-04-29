package org.hld.hugold.controller;

import java.util.ArrayList;
import java.util.List;
import org.hld.hugold.entity.User;
import org.hld.hugold.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	public List<User> getUsers(){
		return userService.getAll();
	}

	@GetMapping(path = "/{id}")
	public User getUser(@PathVariable("id") int id) {
		return userService.getUser(id).get();
	}
	@PostMapping
	public User addUser(@RequestBody User user){	
		return userService.addUser(user);
	}
	@PutMapping
	public User updateUser( @RequestBody User user) {
		return userService.updateUser(user);
		
	}
	
	@DeleteMapping(path= "/{id}")
		public User deleteUser(@PathVariable("id")int id) {
		return userService.deleteUser(id);
	}
	

}
