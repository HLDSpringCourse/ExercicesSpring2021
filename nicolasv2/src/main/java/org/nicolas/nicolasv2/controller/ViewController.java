package org.nicolas.nicolasv2.controller;

import org.nicolas.nicolasv2.entity.User;
import org.nicolas.nicolasv2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ViewController {

    @Autowired
    private UserService UserService;

    @GetMapping("/view")
    public String view(Model model) {
        model.addAttribute("users", UserService.getUsers());
        model.addAttribute("newUsers", new User());
        return "view";
    }

    @GetMapping("/view/{id}")
    public String viewById(@PathVariable String id, Model model) {
        model.addAttribute("user", UserService.getUser(id));
        return "view-id";
    }
}