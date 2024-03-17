package com.simpletask.simplemeal.repository;

import com.simpletask.simplemeal.model.RegularMeal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegularMealRepository extends JpaRepository<RegularMeal, Integer> {
    Optional<RegularMeal> findById(int id);
}
