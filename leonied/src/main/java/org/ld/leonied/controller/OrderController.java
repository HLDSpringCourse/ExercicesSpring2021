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
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<Order>> getOrders() {
        List<Order> orders = orderService.getOrders();
        return ResponseEntity.status(HttpStatus.OK).body(orders);
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        if(order != null) {
            orderService.addOrder(order);
            return ResponseEntity.status(HttpStatus.OK).body(order);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping
    public ResponseEntity<Order> editOrder(@RequestBody Order order) {
        if(order != null) {
            if(orderService.findOrderById(order.getId()) != null) {
                orderService.updateOrder(order);
                return ResponseEntity.status(HttpStatus.OK).body(order);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
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
    public ResponseEntity<List<Order>> searchOrders(@RequestParam(required = false) String name, @RequestParam(required = false) String city) {
        List<Order> orders = orderService.findOrdersByParam(name, city);
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
