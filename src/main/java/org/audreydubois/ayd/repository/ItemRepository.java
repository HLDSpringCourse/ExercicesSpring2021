package org.audreydubois.ayd.repository;

import org.audreydubois.ayd.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
