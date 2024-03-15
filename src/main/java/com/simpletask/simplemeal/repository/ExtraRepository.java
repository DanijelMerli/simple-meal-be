package com.simpletask.simplemeal.repository;

import com.simpletask.simplemeal.model.Extra;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExtraRepository extends JpaRepository<Extra, Integer> {
    Optional<Extra> findById(int id);
}
