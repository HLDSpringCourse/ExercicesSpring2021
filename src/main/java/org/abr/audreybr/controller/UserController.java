package org.abr.audreybr.controller;

import javassist.NotFoundException;
import org.abr.audreybr.entity.User;
import org.abr.audreybr.dto.UserRequest;
import org.abr.audreybr.service.UserService;
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

    @GetMapping("/{id}")
    public User getUser(@PathVariable("id") int id) throws NotFoundException {
        return userService.getUserById(id);
    }

    @GetMapping("/list")
    public List<User> listUsers() {
        return userService.getUsers();
    }

    @GetMapping("/search")
    public User searchUsers(@RequestParam("name") String name) throws NotFoundException {
        return userService.getUserByName(name);
    }

    @PostMapping
    public ResponseEntity<User> addUserWithCity(@RequestBody UserRequest userRequest) throws NotFoundException {
        User user = userService.addUserWithCity(userRequest);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }


    @PutMapping
    public void setUser(@RequestBody UserRequest userReq) throws NotFoundException {
        userService.setUser(userReq);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") int id) throws NotFoundException {
        userService.deleteUserById(id);
    }
}
