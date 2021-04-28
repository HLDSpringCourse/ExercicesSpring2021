package com.tp.tommyp.Repository;

import com.tp.tommyp.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author tommy
 * @created 27/04/2021 - 10:25
 * @project tommyp
 */
public interface ItemRepository extends JpaRepository<Item, Long> {
}
