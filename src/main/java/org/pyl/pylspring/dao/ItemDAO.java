package org.pyl.pylspring.dao;


import org.pyl.pylspring.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ItemDAO extends JpaRepository<Item, Long> {

    Optional<Item> findFirstByRegionNameContaining(String containedString);

    @Query("SELECT i FROM Item i WHERE i.name LIKE :prefix%")
    List<Item> getItemNameByPrefix(String prefix);
}
