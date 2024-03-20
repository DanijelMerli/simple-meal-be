package com.simpletask.simplemeal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simpletask.simplemeal.model.Role;
import com.simpletask.simplemeal.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer>{

    Optional<User> findByEmail(String name);
    
    User findByRole(Role role);

    User findUserByEmail(String email);
	
	boolean existsByEmail(String email);
	
}
