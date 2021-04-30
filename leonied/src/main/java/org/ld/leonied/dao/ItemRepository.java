package org.ld.leonied.dao;

import org.ld.leonied.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Order, Long> {

}
