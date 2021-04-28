package org.ld.leonied.service;

import org.ld.leonied.NotFoundException;
import org.ld.leonied.configuration.RestTemplateConfig;
import org.ld.leonied.entity.City;
import org.ld.leonied.entity.Order;
import org.ld.leonied.entity.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    private List<Order> orders = new ArrayList<>();
    @Autowired
    private RestTemplate restTemplate;

    public void addOrder(Order order) {
        if(orders.size() > 0) {
            order.setId(orders.get(orders.size() - 1).getId() + 1);
        } else {
            order.setId(1);
        }

        City[] cities = findCityNames(order.getLattitude(), order.getLongitude());
        if(cities != null) {
            order.setCity(cities[0].getNom());
        } else {
            order.setLattitude(9999);
            order.setLongitude(9999);
        }

        orders.add(order);
    }

    public void removeOrder(Order order) {
        orders.remove(order);
    }

    public List<Order> getOrders() {
        return orders;
    }

    public Order findOrderById(int id) throws NotFoundException {
        return orders.stream()
                .filter(order -> order.getId() == id)
                .findFirst()
                .orElseThrow(() -> new NotFoundException("La commande d'id " + id + " n'a pas été trouvée"));
    }

    public List<Order> findOrdersByParam(String name, String cityName, Integer cityLat, Integer cityLong) {
        Search.SearchBuilder searchBuilder = new Search.SearchBuilder();
        if(name != null) {
            searchBuilder.name(name);
        }
        if(cityName != null) {
            searchBuilder.cityName(cityName);
        }
        if(cityLat != null) {
            searchBuilder.cityLat(cityLat);
        }
        if(cityLong != null) {
            searchBuilder.cityLong(cityLong);
        }
        return searchBuilder.build().result(orders);
    }


    public void updateOrder(Order order) {
        Order originalOrder = findOrderById(order.getId());

        // update name
        if(order.getName() != null) {
            originalOrder.setName(order.getName());
        }

        // update city by name
        // si lat et/ou lon dispo, priorité pour éviter incohérence
        if(order.getCity() != null && order.getLattitude() == 9999 && order.getLongitude() == 9999) {
            originalOrder.setCity(order.getCity());
            originalOrder.setLattitude(9999);
            originalOrder.setLongitude(9999);
        } else { // update city by location
            City[] cities = findCityNames(order.getLattitude(), order.getLongitude());
            if(cities != null && cities.length > 0) {
                originalOrder.setCity(cities[0].getNom());
                originalOrder.setLattitude(order.getLattitude());
                originalOrder.setLongitude(order.getLongitude());
            } else {
                originalOrder.setCity(null);
                originalOrder.setLattitude(9999);
                originalOrder.setLongitude(9999);
            }
        }
    }

    public City[] findCityNames(int lattitude, int longitude) throws NotFoundException {
        // find city name according to lattitude/longitude
        String requete = "";
        if(lattitude != 9999) {
            requete += "lat=" + lattitude + "&";
        }
        if(longitude != 9999) {
            requete += "lon=" + longitude + "&";
        }
        if(!requete.equals("")) {
            String resourceUrl
                    = "https://geo.api.gouv.fr/communes?";
            ResponseEntity<City[]> response
                    = restTemplate.getForEntity(resourceUrl + requete + "fields=nom", City[].class);
            if(response.getBody() != null && response.getBody().length > 0) {
                return response.getBody();
            } else {
                throw new NotFoundException("Aucune ville ne correspond à ces coordonnées");
            }
        } else {
           return null;
        }
    }
}
