package com.example.auth_demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.auth_demo.entity.AppUser;

public interface UserRepository extends JpaRepository<AppUser, Long>{
}