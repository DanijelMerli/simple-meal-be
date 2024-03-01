package com.simpletask.simplemeal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simpletask.simplemeal.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
