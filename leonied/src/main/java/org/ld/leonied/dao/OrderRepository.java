package org.ld.leonied.dao;

import org.ld.leonied.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    public List<Order> findByNameLike(String name);

    public List<Order> findByCityLike(String city);
}
