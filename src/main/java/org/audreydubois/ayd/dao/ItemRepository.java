package org.audreydubois.ayd.dao;

import org.audreydubois.ayd.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query("select i from Item i where i.regionCode like %:end")
    public List<Item> findByRegionCode(String end);

    public List<Item> findByName(String name);
}
