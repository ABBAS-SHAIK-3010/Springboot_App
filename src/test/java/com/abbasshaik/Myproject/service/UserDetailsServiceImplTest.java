package com.abbasshaik.Myproject.service;

import com.abbasshaik.Myproject.entities.User;
import com.abbasshaik.Myproject.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserDetailsServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Test
    void loadUserByUsernameTest() {

        User mockUser = User.builder()
                .username("Pandey")
                // BCrypt hash for "password"
                .password("$2a$10$7EqJtq98hPqEX7fNZaFWoOhi5YjP7LJ4tZ8kU6B8D1kR3C9XJkR6G")
                .roles(List.of("USER"))
                .build();

        when(userRepository.findByUsername(anyString()))
                .thenReturn(mockUser);

        UserDetails userDetails =
                userDetailsService.loadUserByUsername("Pandey");

        assertNotNull(userDetails);
    }
}
