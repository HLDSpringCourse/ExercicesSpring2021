package controller;

import java.lang.reflect.Array;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import entity.User;
import service.userService;

@RestController
@RequestMapping("/user")
public class userController {
	@Autowired
	

	@GetMapping("/list")
	public User getUser(){
		return new User("1", "Abo");
	}

	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user) throws URISyntaxException{
		return ResponseEntity.status(HttpStatus.CREATED).body(user);
	}
	
	

}
