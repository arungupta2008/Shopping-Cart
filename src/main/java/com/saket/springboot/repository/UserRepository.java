package com.saket.springboot.repository;

import com.saket.springboot.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
