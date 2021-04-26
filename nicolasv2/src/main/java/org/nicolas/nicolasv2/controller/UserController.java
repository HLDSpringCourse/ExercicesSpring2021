package org.nicolas.nicolasv2.controller;

import org.nicolas.nicolasv2.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @PostMapping
    public ResponseEntity<String> addUser(@RequestBody User user) {
        return ResponseEntity.ok(""+ user);
    }
    @GetMapping("/list")
    public ResponseEntity<User> searchUsers(
            @RequestParam("name") String name
    ) {
        User user = new User(1, name);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(
            @PathVariable("id") int id
    ) {
        User user = new User(id, "user"+id);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PutMapping
    public ResponseEntity<User> setUserById(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(
            @PathVariable("id") int id
    ) {
        User user = new User(id, "user"+id);
        return ResponseEntity.ok(user + "");
    }

}