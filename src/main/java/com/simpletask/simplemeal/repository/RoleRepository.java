package com.simpletask.simplemeal.repository;

import com.simpletask.simplemeal.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findById(Integer id);
    Role findByName(String name);
}
