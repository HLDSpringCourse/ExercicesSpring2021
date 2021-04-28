package org.audreydubois.ayd.dao;

import org.audreydubois.ayd.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
