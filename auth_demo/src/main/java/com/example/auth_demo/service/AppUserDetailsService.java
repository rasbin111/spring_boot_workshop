package com.example.auth_demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.example.auth_demo.entity.AppUser;
import com.example.auth_demo.repository.UserRepository;

@Service
public class AppUserDetailsService {
    @Autowired
    private UserRepository userRepository;

    public List<AppUser> getAllUsers() {
        return userRepository.findAll();
    }

    public AppUser getUserById(Long id) {
        // return userRepository.findById(id)
        // .map(user -> ResponseEntity.ok(user))
        // .orElse(ResponseEntity.notFound().build());
        Optional<AppUser> user = userRepository.findById(id);

        if (user.isPresent()) {
            return user.get();
        } else {
            return null;
        }
    }

    public AppUser createUser(AppUser user) {
        Example<AppUser> example = Example.of(user);
        boolean userExists = userRepository.exists(example);
        if (userExists) {
            Optional<AppUser> existingUser = userRepository.findOne(example);
            return existingUser.get();
        } else {
            AppUser newUser = userRepository.save(user);
            return newUser;
        }

    }

    public AppUser updateUser(Long id, AppUser user) {
        boolean userExists = userRepository.existsById(id);
        if (userExists) {
            AppUser putUser = userRepository.save(user);
            return putUser;
        } else {
            AppUser newUser = userRepository.save(user);
            return newUser;
        }
    }

    public boolean deleteUser(Long id) {
        try {
            userRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
