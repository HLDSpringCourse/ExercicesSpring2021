package org.pyl.pylspring.dao;


import org.pyl.pylspring.entity.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemDAO extends CrudRepository<Item, Long> {


}
