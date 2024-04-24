package com.thelegance.bookshalf.repository;

import com.thelegance.bookshalf.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
        User findByUsername(String name);
}
