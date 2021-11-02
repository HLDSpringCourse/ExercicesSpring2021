package org.hld.hugold.service;


import org.hld.hugold.dto.FoundCity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class GeoApiClient {

    private RestTemplate restTemplate;


    public String findCity(String zipCode){
        return restTemplate.getForEntity("https://geo.api.gouv.fr/communes?codePostal=" + zipCode + "&fields=nom&format=json&geometry=centre",FoundCity[].class)
                .getBody()[0].getName();
    }
}
