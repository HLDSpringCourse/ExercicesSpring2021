package org.hld.hugold.controller;


import org.hld.hugold.entity.CustomerEntity;
import org.hld.hugold.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    private final AtomicLong counter = new AtomicLong();

    /***
     * GET
     * @return
     */

    //Get All items
    @GetMapping
    public List<CustomerEntity> all(){
        return customerService.getAllCustomers();
    }


    //Get Single item
    @GetMapping("/{id}")
    public CustomerEntity getCustomer(@PathVariable("id") String id) throws CustomerNotFoundException {

        return customerService.getCustomerById(id);

    }

    /***
     * POST
     *
     */
    //Add item
    @PostMapping
    public CustomerEntity addCustomer(@RequestBody String customer){
        return customerService.addCustomer(customer);

    }


    //Delete item
    @DeleteMapping("/{id}")
    public Long removeCustomer(@PathVariable("id") String id){

        return customerService.removeCustomer(id);
    }


    @PutMapping
    public CustomerEntity updateSujet(@RequestBody CustomerEntity customer) throws CustomerNotFoundException {
        return customerService.updateCustomer(customer);
    }
}
