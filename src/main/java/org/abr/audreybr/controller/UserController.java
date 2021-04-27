package org.abr.audreybr.controller;

import org.abr.audreybr.entity.User;
import org.abr.audreybr.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserRepository repository;

    @GetMapping("/list")
    public List<User> list(){
        List<User> contacts =new ArrayList<>();
        repository.findAll().forEach(contacts::add);
        return users;
    }

}
