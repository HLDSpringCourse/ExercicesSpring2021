package org.abr.audreybr.repository;

import org.abr.audreybr.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
