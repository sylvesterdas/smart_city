package com.lenin.smart_city.controllers;

import com.lenin.smart_city.models.auth.User;
import com.lenin.smart_city.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * AdminProcessingController
 */
@Controller
@RequestMapping(path="/admin/processing")
public class AdminProcessingController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping(path="users")
    public Iterable<User> users() {
        
        return userRepository.findAll();
    }
    
    
}