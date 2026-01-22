package com.abbasshaik.Myproject.service;

import com.abbasshaik.Myproject.entities.User;
import com.abbasshaik.Myproject.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService; // your service class

    @Test
    void testfindByUserName() {
        User mockUser = User.builder()
                .username("Pandey")
                .password("123")
                .build();

        when(userRepository.findByUsername("Pandey"))
                .thenReturn(mockUser);

        User result = userService.findByUsername("Pandey");

        assertNotNull(result);
        assertEquals("Pandey", result.getUsername());
    }
}
