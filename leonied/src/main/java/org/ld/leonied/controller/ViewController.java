package org.ld.leonied.controller;

import org.ld.leonied.NotFoundException;
import org.ld.leonied.dao.OrderRepository;
import org.ld.leonied.entity.Order;
import org.ld.leonied.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ViewController {
    private final OrderService orderService;

    public ViewController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(path = "/index")
    public String index(Model model) {
        model.addAttribute("orders", orderService.getOrders());
        model.addAttribute("order", new Order());
        model.addAttribute("recherche", new Order());
        return "index";
    }

    @GetMapping(path ="/show/{id}")
    public String show(Model model, @PathVariable Long id) {
        model.addAttribute("order", orderService.findOrderById(id));
        return "show";
    }

    @PostMapping(path ="/create")
    public String create(@ModelAttribute Order order) {
        orderService.addOrder(order);
        return "redirect:/index";
    }

    @PostMapping(path ="/search1")
    public String search1(@ModelAttribute Order recherche, Model model) {
        model.addAttribute("orders", orderService.findOrderByName(recherche.getName()));
        return "resultats";
    }

    @PostMapping(path ="/search2")
    public String search2(@ModelAttribute Order recherche, Model model) {
        model.addAttribute("orders", orderService.findOrdersByParam(recherche.getName(), recherche.getCity(), recherche.getLattitude(), recherche.getLongitude()));
        return "resultats";
    }

    @PostMapping(path ="/search3")
    public String search3(@ModelAttribute Order recherche, Model model) {
        model.addAttribute("orders", orderService.findOrderBetweenLattitudes(recherche.getLattitude(), recherche.getLongitude()));
        return "resultats";
    }

    @DeleteMapping(path = "/delete/{id}")
    public String delete(@PathVariable Long id) throws NotFoundException {
        Order order = orderService.findOrderById(id);
        orderService.removeOrder(order);
        return "redirect:/index";
    }
}
