package com.example.Abo.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.Abo.dto.FoundDep;
import com.example.Abo.entity.User;


@Service
public class userService {
	
	
    private RestTemplate restTemplate = new RestTemplate() ;
    
	
	private List<User> users = new ArrayList<>();
	
    public List<User> getUser(){
    	return users ;
    }
    
    public User addUser(User user) {
    	user.setDepartement(findDept(user.getDepartementCode()));
    	
    	users.add(user);
    	
    	return user ;
    }
    
    private String findDept(String DepCode) {
        return restTemplate.getForObject("https://geo.api.gouv.fr/departements/"+DepCode+"?fields=nom", FoundDep.class).getNom();
    }
}
