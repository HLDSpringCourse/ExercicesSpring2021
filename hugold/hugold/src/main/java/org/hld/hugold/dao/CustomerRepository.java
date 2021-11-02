package org.hld.hugold.dao;

import org.hld.hugold.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity,String> {

    @Query("select c from Customer c  where c.city like %:end")
    List<CustomerEntity> findByCityEndWith(String end);
    List<CustomerEntity> findByName(String name);

}
