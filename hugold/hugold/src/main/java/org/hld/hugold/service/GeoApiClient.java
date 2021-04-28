package org.hld.hugold.service;

import io.swagger.models.HttpMethod;
import org.hld.hugold.config.RestTemplateConfiguration;
import org.hld.hugold.controller.CustomerNotFoundException;
import org.hld.hugold.dto.FoundCity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeoApiClient {
    @Autowired
    private RestTemplateConfiguration restTemplateConfiguration;

    private String findCity(String zipCode){
        return restTemplateConfiguration.restTemplate().exchange(
                "https://geo.api.gouv.fr/communes?codePostal=" + zipCode + "&fields=nom&format=json&geometry=centre",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<FoundCity>>() {
                })
                .getBody()
                .stream()
                .findFirst()
                .map(FoundCity::getName)
                .orElseThrow(() -> new CustomerNotFoundException(
                        "The City with the zipcode: " + zipCode + " is nowhere to be found!!"));
        )
    }
}
