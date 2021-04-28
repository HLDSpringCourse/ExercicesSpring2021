package org.hld.hugold.service;

import org.hld.hugold.controller.CustomerNotFoundException;
import org.hld.hugold.entity.CustomerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {


    @Autowired
    private GeoApiClient geoApiClient;

    //Data container
    private List<CustomerEntity> customers = new ArrayList<>();


    //Get all data
    public List<CustomerEntity> getAllCustomers(){
        return customers;
    }



    //Get specific data using ID
    public CustomerEntity getCustomerById(String id) throws CustomerNotFoundException {
        return customers.stream()
                .filter(c -> c.getId().equals(id))
                .findAny()
                .orElseThrow(()-> new CustomerNotFoundException("The customer ain't nowhere to be found"));
    }


    //Add new Customer
    public CustomerEntity addCustomer(String name){
        final CustomerEntity customer = new CustomerEntity(name, zipcode);
        customers.add(customer);

        return customer;
    }


    //UPDATE Customer by ID
        public CustomerEntity updateCustomer(CustomerEntity customer) throws CustomerNotFoundException{


        final Optional<CustomerEntity> updatedItem = customers.stream().filter(c -> c.getId().equals(customer.getId())).findAny();

        if(updatedItem.isEmpty()){
            throw  new CustomerNotFoundException("Customer doesn't exist !!");

        }else {
            final CustomerEntity updatedCustomer = updatedItem.get();
            customers.remove(updatedCustomer);
            updatedCustomer.setName(customer.getName());
            customers.add(updatedCustomer);

            return updatedCustomer;
        }


    }


    //Remove customer by ID
    public Long removeCustomer(String id){
        final long removedItem = customers.stream().filter( c -> c.getId().equals(id)).count();
        customers.removeIf(c->c.getId().equals(id));


        return removedItem;


    }


}
