package org.hld.hugold.controller;


import org.hld.hugold.dto.CustomerDto;
import org.hld.hugold.entity.CustomerEntity;
import org.hld.hugold.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    /***
     * GET
     */

    //Get All items
    @GetMapping
    public List<CustomerDto> getAllCustomers(@RequestParam(name = "name",required = false) String name){
        return name == null ? customerService.getAllCustomerDto() :  null;
    }



    //Get Single item
    @GetMapping("/{id}")
    public void getCustomer(@PathVariable("id") String id) throws CustomerNotFoundException {

//        return customerService.getCustomerById(id);

    }

    /***
     * POST
     *
     */
    //Add item
    @PostMapping
    public CustomerDto addCustomer(@RequestBody CustomerDto customer){
        return customerService.addCustomer(customer);

    }


    //Delete item
    @DeleteMapping("/{id}")
    public void  removeCustomer(@PathVariable("id") String id){

//        return customerService.removeCustomer(id);
    }


    @PutMapping
    public void updateSujet(@RequestBody CustomerEntity customer) throws CustomerNotFoundException {
//        return customerService.updateCustomer(customer);
    }
}
