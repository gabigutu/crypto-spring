package com.crypto.exchange.security;

import com.crypto.exchange.entities.User;
import com.crypto.exchange.repositories.UserRepository;
import com.crypto.exchange.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Primary
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserService userService;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userDB = userService.findByUsername(username);
        System.out.println("User from database is " + userDB.getUsername());
        MyUserDetails myUserDetails = new MyUserDetails(userDB);
        return myUserDetails;
    }
}
