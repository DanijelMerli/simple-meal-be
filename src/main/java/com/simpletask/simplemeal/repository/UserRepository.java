package com.simpletask.simplemeal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simpletask.simplemeal.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer>{

    Optional<User> findByEmail(String name);

    User findUserByEmail(String email);
}
