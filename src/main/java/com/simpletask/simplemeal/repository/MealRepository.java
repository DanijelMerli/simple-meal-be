package com.simpletask.simplemeal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simpletask.simplemeal.model.Meal;

import java.util.Optional;

public interface MealRepository extends JpaRepository<Meal, Integer>{
    Optional<Meal> findById(int id);
}
