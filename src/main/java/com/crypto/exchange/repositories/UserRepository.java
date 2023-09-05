package com.crypto.exchange.repositories;

import com.crypto.exchange.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    // return user from username and password
    User findByUsernameAndPassword(String username, String password);

    User findByUsername(String username);

}
