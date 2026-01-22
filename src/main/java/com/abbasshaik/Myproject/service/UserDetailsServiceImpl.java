package com.abbasshaik.Myproject.service;

import com.abbasshaik.Myproject.entities.User;
import com.abbasshaik.Myproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user != null){
            UserDetails userDetails = org.springframework.security.core.userdetails.User
                    .withUsername(user.getUsername())
                    .password(user.getPassword()) // already BCrypt-encrypted
                    .roles(user.getRoles().toArray(new String[0]))        // e.g. ADMIN / USER
                    .build();
            return userDetails;
        }
        throw new UsernameNotFoundException("User not found with Username"+ username);
    }
}
