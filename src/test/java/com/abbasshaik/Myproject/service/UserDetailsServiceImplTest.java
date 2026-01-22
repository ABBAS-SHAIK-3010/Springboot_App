package com.abbasshaik.Myproject.service;

import com.abbasshaik.Myproject.entities.User;
import com.abbasshaik.Myproject.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;

import static org.mockito.Mockito.*;


public class UserDetailsServiceImplTest {
    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;
    @Mock
    private UserRepository userRepository;

    void setUp(){
        MockitoAnnotations.initMocks(this);
    }
    @Test
    void loadUserByUsernameTest(){
        when(userRepository.findByUsername(ArgumentMatchers.anyString())).thenReturn(User.builder().username("Pandey").password("Adarsh").roles(new ArrayList<>()).build());
        UserDetails user = userDetailsService.loadUserByUsername("Pandey");
        Assertions.assertNotNull(user);
    }
}
