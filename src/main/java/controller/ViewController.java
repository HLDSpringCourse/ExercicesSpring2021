package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/view")
public class ViewController {
	@GetMapping(value="")
	// public String findOwner(@PathVariable String ownerId, Model model) {
	public String findOwner(Model model) {
		model.addAttribute("helloKey", "Hello World");
		return "view";
	}
}
