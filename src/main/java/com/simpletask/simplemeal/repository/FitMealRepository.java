package com.simpletask.simplemeal.repository;

import com.simpletask.simplemeal.model.FitMeal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FitMealRepository extends JpaRepository<FitMeal, Integer> {
    Optional<FitMeal> findById(int id);
}
