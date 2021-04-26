package com.alik.alik.service;

import com.alik.alik.entity.Customer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
    private List<Customer> customers = new ArrayList<>();

    public List<Customer> getCustomers(){
        return customers;
    }
    public Customer getCustomer(int id){
        try {
            Customer customer  = customers.get(id);
            return customer;
        }catch (Exception e){
            return null;
        }
    }

    public void addCustomer(Customer customer){
        customers.add(customer);
    }

    public void deleteCustomer(int id){
        customers.remove(id);
    }
}
