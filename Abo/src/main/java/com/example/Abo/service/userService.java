package com.example.Abo.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Abo.entity.User;


@Service
public class userService {
	
	private List<User> users = new ArrayList<>();
	
    public List<User> getUser(){
    	return users ;
    }
    
    public User addUser(User user) {
    	
    	users.add(user);
    	
    	return user ;
    }
}
