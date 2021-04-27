package org.aubm.aubertma.controller;

import org.aubm.aubertma.entity.Customer;
import org.aubm.aubertma.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers(){
        return new ResponseEntity<>(customerService.getCustomers(), HttpStatus.OK);
    }
    @GetMapping({"/{id}"})
    public ResponseEntity<Customer> getCustomerById(@PathVariable int id){
        return new ResponseEntity<>(customerService.getCustomerById(id),HttpStatus.OK);
    }
    @PostMapping
    public void addCustomer(@RequestBody Customer newCustomer){
        customerService.addCustomer(newCustomer);
        System.out.println("ajout d'un nouveau client" + newCustomer.getName());
    }

    @PutMapping({"/{id}"})
    public void updateCustomer(@PathVariable int id, @RequestBody Customer customer){
        customerService.updateCustomer(id,customer);
        System.out.println("client à jour");
    }
    @DeleteMapping
    public void deleteCustomer(int id){
        customerService.removeCustomer(id);
        System.out.println("suppression du client");
    }
}
