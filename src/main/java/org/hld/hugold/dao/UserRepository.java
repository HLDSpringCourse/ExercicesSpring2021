package org.hld.hugold.dao;

import java.util.List;
import java.util.Optional;

import org.hld.hugold.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	public List<User> findAll();
			
	public void delete(User entity);
		
	public Optional<User> findById(Integer id);
	
	public User save(User entity);

}
