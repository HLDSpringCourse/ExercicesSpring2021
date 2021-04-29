package org.pyl.pylspring.dao;


import org.pyl.pylspring.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ItemDAO extends JpaRepository<Item, Long> {


}
