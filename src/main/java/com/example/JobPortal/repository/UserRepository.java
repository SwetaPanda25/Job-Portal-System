package com.example.JobPortal.repository;

import com.example.JobPortal.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}

