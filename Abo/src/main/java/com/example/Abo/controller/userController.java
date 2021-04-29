package com.example.Abo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Abo.entity.User;
import com.example.Abo.service.userService;

import javassist.NotFoundException;

@RestController
@RequestMapping("/user")
public class userController {
	@Autowired
	private userService service;

	@GetMapping("/list")
	public List<User> getUser(){
		return service.getUser();
	}

	@PostMapping
	public User addUser(@RequestBody User user) throws NotFoundException{
		return service.addUser(user);
	}
	
	@GetMapping
    public List<User> getUsers() {
        return service.getUser();
    }
	

}
