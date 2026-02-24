package com.example.auth_demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
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
import com.example.auth_demo.repository.UserRepository;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public @ResponseBody List<AppUser> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<AppUser> getUserById(@PathVariable Long id) {
        // return userRepository.findById(id)
        // .map(user -> ResponseEntity.ok(user))
        // .orElse(ResponseEntity.notFound().build());
        Optional<AppUser> user = userRepository.findById(id);

        if (user.isPresent()) {
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/users")
    public ResponseEntity<AppUser> createUser(@RequestBody AppUser user) {
        Example<AppUser> example = Example.of(user);
        boolean userExists = userRepository.exists(example);
        if (userExists) {
            Optional<AppUser> existingUser = userRepository.findOne(example);
            return new ResponseEntity<AppUser>(existingUser.get(), HttpStatus.CONFLICT);
        } else {
            AppUser newUser = userRepository.save(user);
            return new ResponseEntity<AppUser>(newUser, HttpStatus.CREATED);
        }

    }

    @PutMapping("/users/{id}")
    public ResponseEntity<AppUser> updateUser(@PathVariable Long id, @RequestBody AppUser user) {
        boolean userExists = userRepository.existsById(id);
        if (userExists) {
            AppUser putUser = userRepository.save(user);
            return new ResponseEntity<AppUser>(putUser, HttpStatus.OK);
        } else {
            AppUser newUser = userRepository.save(user);
            return new ResponseEntity<AppUser>(newUser, HttpStatus.CREATED);
        }
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable Long id) {
        try {
            userRepository.deleteById(id);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
