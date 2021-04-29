package org.ld.leonied.dao;

import org.ld.leonied.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    public List<Order> findByNameLike(String name);

    public List<Order> findByCityLike(String city);

    @Query("SELECT o FROM Order o WHERE o.lattitude BETWEEN :lat1 AND :lat2 ORDER BY o.city ASC")
    public List<Order> findBetweenTwoLattitudes(Integer lat1, Integer lat2);
}
