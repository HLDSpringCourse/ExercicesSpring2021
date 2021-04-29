package org.ld.leonied.service;

import org.ld.leonied.NotFoundException;
import org.ld.leonied.dao.OrderRepository;
import org.ld.leonied.entity.City;
import org.ld.leonied.entity.Order;
import org.ld.leonied.entity.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private OrderRepository orderRepository;

    public void addOrder(Order order) {
        City[] cities = findCityNames(order.getLattitude(), order.getLongitude());
        if(cities != null) {
            order.setCity(cities[0].getNom());
        } else {
            order.setLattitude(null);
            order.setLongitude(null);
        }

        orderRepository.save(order);
    }

    public void removeOrder(Order order) {
        orderRepository.delete(order);
    }

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    public Order findOrderById(Long id) throws NotFoundException {
        Optional<Order> order = orderRepository.findById(id);
        if(order.isEmpty()) {
            throw new NotFoundException("La commande d'id " + id + " n'a pas été trouvée");
        } else {
            return order.get();
        }
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
        return searchBuilder.build().result(orderRepository.findAll());
    }


    public void updateOrder(Order order) {
        Order originalOrder = findOrderById(order.getId());

        // update name
        if(order.getName() != null) {
            originalOrder.setName(order.getName());
        }

        // update city by name
        // si lat et/ou lon dispo, priorité pour éviter incohérence
        if(order.getCity() != null && order.getLattitude() == null && order.getLongitude() == null) {
            originalOrder.setCity(order.getCity());
            originalOrder.setLattitude(null);
            originalOrder.setLongitude(null);
        } else { // update city by location
            City[] cities = findCityNames(order.getLattitude(), order.getLongitude());
            if(cities != null && cities.length > 0) {
                originalOrder.setCity(cities[0].getNom());
                originalOrder.setLattitude(order.getLattitude());
                originalOrder.setLongitude(order.getLongitude());
            } else {
                originalOrder.setCity(null);
                originalOrder.setLattitude(null);
                originalOrder.setLongitude(null);
            }
        }
    }

    public City[] findCityNames(Integer lattitude, Integer longitude) throws NotFoundException {
        // find city name according to lattitude/longitude
        String requete = "";
        if(lattitude != null) {
            requete += "lat=" + lattitude + "&";
        }
        if(longitude != null) {
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
