package org.ld.leonied.service;

import org.ld.leonied.entity.Order;
//import org.ld.leonied.entity.Search;
import org.ld.leonied.entity.Search;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private List<Order> orders = new ArrayList<>();

    public void addOrder(Order order) {
        if(orders.size() > 0) {
            order.setId(orders.get(orders.size() - 1).getId() + 1);
        } else {
            order.setId(1);
        }

        orders.add(order);
    }

    public void removeOrder(Order order) {
        orders.remove(order);
    }

    public List<Order> getOrders() {
        return orders;
    }

    public Order findOrderById(int id) {
        List<Order> resultat = orders.stream().filter(order -> order.getId() == id).collect(Collectors.toList());
        return resultat.size() > 0 ? resultat.get(0) : null;
    }

    public List<Order> findOrdersByParam(String name, String cityName) {
        Search.SearchBuilder searchBuilder = new Search.SearchBuilder();
        if(name != null) {
            searchBuilder.name(name);
        }
        if(cityName != null) {
            searchBuilder.cityName(cityName);
        }
        return searchBuilder.build().result(orders);
    }


    public void updateOrder(Order order) {
        Order originalOrder = findOrderById(order.getId());
        orders.set(orders.indexOf(originalOrder), order);
    }
}
