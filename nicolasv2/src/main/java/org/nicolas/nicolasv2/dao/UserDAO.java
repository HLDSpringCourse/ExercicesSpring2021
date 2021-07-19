package org.nicolas.nicolasv2.dao;


import org.nicolas.nicolasv2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface UserDAO extends JpaRepository<User, Integer> {




}