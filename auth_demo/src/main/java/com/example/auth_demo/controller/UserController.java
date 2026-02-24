package com.example.auth_demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.auth_demo.entity.AppUser;
import com.example.auth_demo.service.AppUserDetailsService;

@Controller
public class UserController {

    @Autowired
    private AppUserDetailsService appUserDetailsService;

    @GetMapping("/users")
    public @ResponseBody List<AppUser> getAllUsers() {
        return appUserDetailsService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<AppUser> getUserById(@PathVariable Long id) {
        AppUser user = appUserDetailsService.getUserById(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/users")
    public ResponseEntity<AppUser> createUser(@RequestBody AppUser user) {
        AppUser newUser = appUserDetailsService.createUser(user);
        return new ResponseEntity<AppUser>(newUser, HttpStatus.CREATED);

    }

    @PutMapping("/users/{id}")
    public ResponseEntity<AppUser> updateUser(@PathVariable Long id, @RequestBody AppUser user) {
        AppUser updateUser = appUserDetailsService.updateUser(id, user);
        return new ResponseEntity<AppUser>(updateUser, HttpStatus.OK);

    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable Long id) {
        boolean isDeleted = appUserDetailsService.deleteUser(id);
        if (isDeleted) {
            return new ResponseEntity<Boolean>(isDeleted, HttpStatus.OK);
        } else {
            return new ResponseEntity<Boolean>(isDeleted, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
