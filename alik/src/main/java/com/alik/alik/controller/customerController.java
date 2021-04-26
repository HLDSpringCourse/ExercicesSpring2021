package com.alik.alik.controller;

import com.alik.alik.entity.Customer;
import com.alik.alik.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class customerController {

    @Autowired
    private CustomerService customerService;

    private int counter = 0;

    @GetMapping
    public List<Customer> getCustomers(){
        return customerService.getCustomers();
    }

    @GetMapping("/{id}")
    public Customer getCustomer(@PathVariable int id){
        return customerService.getCustomer(id);
    }

    @PostMapping
    public Customer addCustomer(@RequestBody Customer customer){
        Customer newCustomer = new Customer(counter,customer.getName());
        customerService.addCustomer(newCustomer);
        counter++;
        return newCustomer;
    }

    @DeleteMapping("{id}")
    public Customer deleteCustomer(@PathVariable int id){
        Customer customer = customerService.getCustomer(id);
        customerService.deleteCustomer(id);
        return customer;
    }
}
