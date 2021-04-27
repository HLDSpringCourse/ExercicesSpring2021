package org.nicolas.nicolasv2.controller;


import org.nicolas.nicolasv2.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class UserTestController {


    @GetMapping("/username/{UserName}")
    public String getTest(@PathVariable("UserName") String UserName) {
        return UserName;
    }

/*    @GetMapping("/username/{UserName}")
    public String getTest(@RequestBody String UserName) {
        return UserName;
    }*/

    @GetMapping("/user/{id}")
    public int getTest(@PathVariable("id") int id) {
        return id;
    }

}