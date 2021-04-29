package org.aubm.aubertma.service;

import org.aubm.aubertma.entity.City;
import org.aubm.aubertma.entity.Customer;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    private List<Customer> customerList= new ArrayList<>();

    public List<Customer> getCustomers(){

        return customerList;
    }

    public Customer getCustomerById(int id){
        Customer customToReturn=null;
        for (Customer custom:
             customerList) {
            if (custom.getId()==id)
                customToReturn = custom;
        }
        return customToReturn;
    }

    public void addCustomer(Customer newCustomer){
        try {
            newCustomer.setCity(getCity(newCustomer.getCodeINSEE()).getNom());
        } catch (RestClientException e){
            System.out.println("La recherche n'a pas abouti!");
        }
        customerList.add(newCustomer);
    }

    public City getCity(String code){

        RestTemplate restTemplate = new RestTemplate();
       City response
                = restTemplate.getForObject("https://geo.api.gouv.fr/communes/"+code+"?fields=nom,&format=json&geometry=centre", City.class);
        return response;
    }

    public void updateCustomer (int id, Customer newCustomer){
        for (Customer custom:
                customerList) {
            if (custom.getId()==id) {
                custom.setName(newCustomer.getName());
                if (!newCustomer.getCodeINSEE().equals(custom.getCodeINSEE())) {
                    try {
                        custom.setCity(getCity(newCustomer.getCodeINSEE()).getNom());
                    } catch (RestClientException e){
                        System.out.println("La recherche n'a pas abouti!");
                    }
                }
            }
        }
    }

    public void removeCustomer (int id){
        customerList.remove(getCustomerById(id));
    }
}
