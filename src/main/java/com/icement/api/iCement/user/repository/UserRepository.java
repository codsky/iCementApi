package com.icement.api.iCement.user.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.icement.api.iCement.user.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String username);
    
}
