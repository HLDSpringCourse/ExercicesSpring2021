package org.hld.hugold.controller;

import org.hld.hugold.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/vue")
public class ViewController {

	@Autowired
	private UserService userService;
	
	@GetMapping
	public String getVue(Model model) {
		model.addAttribute("users", userService.getAll());
		return "Vue";
	}
	
	
	@GetMapping("/{id}")
	public String getVueid(@PathVariable("id") int id, Model model) {
		model.addAttribute("user", userService.getUser(id));
		return "Vue-id";
	}
}
