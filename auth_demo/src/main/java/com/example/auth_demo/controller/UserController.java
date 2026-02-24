package com.example.auth_demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.auth_demo.entity.AppUser;
import com.example.auth_demo.repository.UserRepository;

@Controller
public class UserController {
    
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public @ResponseBody List<AppUser> getAllUsers(){
        return userRepository.findAll();
    }
}
