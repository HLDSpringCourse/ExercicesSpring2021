package org.nicolas.nicolasv2.controller;

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
    public ResponseEntity<User> addUser(@RequestBody String UserName) {

        User user = userService.addUser(UserName);


        return ResponseEntity.status(HttpStatus.CREATED).body(user);


    }


    @GetMapping("/list")
    public ResponseEntity<List<User>> listUsers() {
        HttpStatus status = HttpStatus.NOT_FOUND;

        List<User> orders = userService.getUsers();


        return ResponseEntity.status(HttpStatus.OK).body(orders);
    }
/*    @GetMapping("/list")
    public List<User> getUsers(){
        List<User> list = new ArrayList<User>();

        list.add(new User(18,"Pedro"));
        list.add(new User(26, "Jean Castex"));
        list.add(new User(18,"Roselyne Bachelot"));
        list.add(new User(26, "Olivier véran"));
        return list;

        //return ResponseEntity.status(HttpStatus.OK).body(user);
    }*/

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") int id) {

        User user = userService.getUserById(id);


        return ResponseEntity.status(HttpStatus.OK).body(user);
        //return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody User user) {

/*
        return user;
*/

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> deleteUser(@PathVariable("id") int id) {


/*
            return id;
*/
            return ResponseEntity.status(HttpStatus.OK).body(id);

    }

}