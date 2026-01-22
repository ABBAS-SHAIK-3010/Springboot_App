package com.abbasshaik.Myproject.controller;

import com.abbasshaik.Myproject.entities.User;
import com.abbasshaik.Myproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    private UserService userservice;
    @PostMapping("/register")
    public void createUser(@RequestBody User username){
        userservice.saveNewUser(username);
    }
}
