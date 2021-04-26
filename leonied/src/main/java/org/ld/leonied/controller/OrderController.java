package org.ld.leonied.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.ld.leonied.entity.Order;
import org.ld.leonied.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(path = "/list")
    public ResponseEntity<List<Order>> getOrders() {
        List<Order> orders = orderService.getOrders();
        return ResponseEntity.status(HttpStatus.OK).body(orders);
    }

    @PostMapping(path = "/edit")
    public ResponseEntity<Order> editOrder(@RequestBody Order order) {
        if(order != null) {
            if(order.getId() == 0) {
                orderService.addOrder(order);
            } else if(orderService.findOrderById(order.getId()) != null) {
                orderService.findOrderById(order.getId()).setName(order.getName());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.status(HttpStatus.OK).body(order);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable int id) {
        Order order = orderService.findOrderById(id);
        if(order != null) {
            return ResponseEntity.status(HttpStatus.OK).body(order);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @RequestMapping()
    public ResponseEntity<List<Order>> searchOrders(@RequestParam(required = false) String name) {
        List<Order> orders = orderService.findOrdersByName(name);
        return ResponseEntity.status(HttpStatus.OK).body(orders);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable int id) {
        Order order = orderService.findOrderById(id);
        if(order != null) {
            orderService.removeOrder(order);
            return ResponseEntity.status(HttpStatus.OK).body("La commande a bien été supprimée");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La commande demandée n'existe pas");
        }
    }
}
