package org.nicolas.nicolasv2.controller;

import org.nicolas.nicolasv2.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
    @GetMapping("/list")
    public List<User> getUsers(){
        List<User> list = new ArrayList<User>();

        list.add(new User(18,"Pedro"));
        list.add(new User(26, "Jean Castex"));
        list.add(new User(18,"Roselyne Bachelot"));
        list.add(new User(26, "Olivier véran"));
        return list;

        //return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable("id") int id) {
        return new User(666, "Jean-Michel Blanquer");
        //return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PutMapping
    public User updateUser(@RequestBody User user) {

        return user;
        //return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @DeleteMapping("/{id}")
    public int deleteUser(@PathVariable("id") int id) {


            return id;
    }

}