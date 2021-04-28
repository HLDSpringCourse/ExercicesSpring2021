package org.nicolas.nicolasv2.controller;

import org.nicolas.nicolasv2.NotFoundException;
import org.nicolas.nicolasv2.entity.User;
import org.nicolas.nicolasv2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;

    @PostMapping
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @DeleteMapping("/{id}")
    public long deleteUser(@PathVariable("id") String id) {
        return userService.deleteUser(id);
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable("id") String id) throws NotFoundException {
        return userService.getUser(id);
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @PutMapping
    public User updateUser(@RequestBody User user) throws NotFoundException {
        return userService.updateUser(user);
    }

}