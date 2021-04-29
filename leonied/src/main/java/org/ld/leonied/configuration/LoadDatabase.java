package org.ld.leonied.configuration;

import org.ld.leonied.dao.OrderRepository;
import org.ld.leonied.entity.Item;
import org.ld.leonied.entity.Order;
import org.ld.leonied.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class LoadDatabase implements ApplicationRunner {
    private OrderRepository orderRepository;
    private OrderService orderService;

    @Autowired
    public LoadDatabase(OrderRepository orderRepository, OrderService orderService) {
        this.orderRepository = orderRepository;
        this.orderService = orderService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        long count = orderRepository.count();

        if (count == 0) {
            Order order = new Order("Init", 45, 5);
            order.addItem(new Item("Machin"));
            order.addItem(new Item("Truc"));
            orderService.addOrder(order);
            orderRepository.save(order);
        }
    }
}
